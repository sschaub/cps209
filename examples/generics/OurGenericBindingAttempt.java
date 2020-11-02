public class OurGenericBindingAttempt {
    public static void main(String[] args) {
        var str1 = new ObservableValue<String>("Hello");
        var str2 = new ObservableValue<String>("There");

        System.out.println("str1 = " + str1.get());
        System.out.println("str2 = " + str2.get());

        str1.bind(str2);
        str2.set("Blue");

        System.out.println("str1 = " + str1.get());
    }
}

class ObservableValue<T> {
    T value;

    ObservableValue<T> observer;

    public ObservableValue(T initValue) {
        value = initValue;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        value = newValue;
        observer.update(newValue);
    }

    public void bind(ObservableValue<T> source) {
        source.observer = this;
    }

    public void update(T newValue) {
        value = newValue;
    }
}

