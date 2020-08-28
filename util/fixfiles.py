import os
import shutil
import re
import sys

def scan(folder: str, recursive=False):
    p = re.compile('([a-z]+)([A-Z][A-Za-z0-9.]*)')

    for file in os.listdir(folder):

        m = p.match(file)
        full_filename = os.path.join(folder, file)
        if m:
            (fileFolder, filename) = m.group(1, 2)
            os.makedirs(os.path.join(folder, fileFolder), exist_ok=True)
            shutil.move(full_filename, os.path.join(folder, fileFolder, filename))
        elif os.path.isdir(full_filename) and recursive:
            scan(full_filename)

foldername = sys.argv[1]

scan('.', False)
scan(foldername, True)


