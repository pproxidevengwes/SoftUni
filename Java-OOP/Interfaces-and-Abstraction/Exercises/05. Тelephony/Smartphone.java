package telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private final List<String> numbers;
    private final List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        this.urls.forEach(e -> sb.append(e.matches("^[^0-9]+$") ? String.format("Browsing: %s!", e) : "Invalid URL!")
                .append(System.lineSeparator()));
        return sb.toString();

    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        this.numbers.forEach(e -> sb.append(e.matches("^[0-9]+$") ? String.format("Calling... %s", e) : "Invalid number!")
                .append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
