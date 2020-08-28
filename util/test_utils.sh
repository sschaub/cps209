MAX_LINES=100

# If TestOutput function not defined, define it here for local testing purposes
if ! type TestOutput &>/dev/null
then
function TestOutput {
  echo "$*"
}
fi

function report_fail {
    TestOutput "$test_category" "$test_name" false "$1"
}

function report_success {
    TestOutput "$test_category" "$test_name" true "$1"
}

# Display contents of __output.log
# Usage: show_output [ max_lines ]
function show_output {
  local max_lines head_count 

  max_lines=$MAX_LINES
  if [ -n "$1" ]; then
  	max_lines=$1
  fi
  
  echo "Output:"

  let head_count=max_lines+1
  
  # Display trimmed output
  line_count=$(head -n $head_count __output.log | wc -l)
  head -n $max_lines __output.log
  if [ $line_count -gt $max_lines ]; then
    echo "... additional lines have been omitted ..."
  fi

  if [ -r "$expected_fn" ]; then
      if [[ $result -eq 125 || -z "$diff_cmd" ]]; then
        echo ""
        echo "========================================================="
        echo "EXPECTED OUTPUT"
        echo "========================================================="
        cat $expected_fn 
      fi
  fi
}


# Run a command with a specified timeout
#
# Usage: run_cmd [ --timeout <timeout> ] [ --success-msg <success_msg> ] [ --maxlines <max_lines> ] [ --expected <expected_fn> ] cmd
#
# --timeout: specify maximum runtime in seconds (default 30)
# --successmsg: optional message to display on success
# --maxlines: maximum lines of output to display on success/failure
# --expected: filename containing expected output 
#
# Sets following variables:
#   success_msg
#   timeout
#   max_lines
#   expected_fn
#
# Writes output to __output.log for use by show_output
function run_cmd {
    success_msg=""
    timeout="5"
    opt_check=1
    must_match=0
    expected_fn=""
    while [ $opt_check -eq 1 ]; do
      if [ "$1" == "--successmsg" ]; then
        success_msg=$2
        shift 2
      elif [ "$1" == "--timeout" ]; then
        timeout=$2
        shift 2
      elif [ "$1" == "--maxlines" ]; then
        max_lines=$2
        shift 2
      elif [ "$1" == "--expected" ]; then      
        expected_fn=$2
        shift 2
      else
        opt_check=0
      fi
    done

    cmd=$*
    echo "Executing: $*"
    #timeout $timeout $cmd > __output.log 2>&1
    $cmd > __output.log 2>&1
    result=$?

    if [ $result -eq 0 ]; then
      if [ -n "$diff_cmd" ]; then
        if $diff_cmd &>/dev/null; then
          echo "*** Correct Result Detected***" >> __output.log
        else
          result=125
        fi
      fi
    fi

    return $result

}

function report_result {
  result=$1

  if [ $result -eq 0 ]; then     
      if [ -z "$success_msg" ]; then
        report_success "Executing \"$cmd\" produced $(show_output $max_lines $result $expected_fn ) "
      else
        report_success "Executing \"$cmd\": $success_msg"
      fi
  else
      if [ $result -eq 124 ]; then
        report_fail "Executing \"$cmd\" exceeded $timeout sec. timeout: $(show_output $max_lines $result)"
      elif [ $result -eq 125 ]; then
        report_fail "Executing \"$cmd\" produced incorrect result: $(show_output $max_lines $result)"
      else
        report_fail "Executing \"$cmd\" FAILED: $(show_output $max_lines $result)"
      fi
  fi

}

# Runs a command, and reports the result
# Usage: See run_cmd above
function run_cmd_and_report {
  max_lines=$MAX_LINES

  test_category=$1
  shift
  test_name=$1
  shift

  run_cmd $*
  result=$?
  report_result $result
  return $result

}


function compile_check {
  if STDERR=$($* 2>&1); then
    TestOutput "Must Pass" "Compile Check" true "$STDERR"
  else
    TestOutput "Must Pass" "Compile Check" false "$STDERR"
    exit 1
  fi
 
}

function pdf_report_check {
  if file $1 | grep "PDF document"; then
    result=true
  else
    result=false  
  fi
  TestOutput "Must Pass" "PDF Report" $result "$(file $1)"

}

alias arun=build/install/app/bin/app
