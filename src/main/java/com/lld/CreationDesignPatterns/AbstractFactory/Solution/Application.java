package com.lld.CreationDesignPatterns.AbstractFactory.Solution;

interface Button {
    void render();
}

interface ScrollBar {
    void scroll();
}

class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering windows button");
    }
}

class WindowsScrollBar implements ScrollBar {
    @Override
    public void scroll() {
        System.out.println("Rendering windows scrollbar");
    }
}

// Mac UI components
class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering MacOS button");
    }
}

class MacOSScrollBar implements ScrollBar {
    @Override
    public void scroll() {
        System.out.println("Rendering MacOS scrollbar");
    }
}

// Abstract factory interface
interface UIFactory {
    Button createButton();
    ScrollBar createScrollBar();
}

// Concrete implementations of the abstract factory
class WindowsFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new WindowsScrollBar();
    }
}

class MacOSFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new MacOSScrollBar();
    }
}

public class Application {
    Button button;
    ScrollBar scrollBar;

    Application(UIFactory factory) {
        button = factory.createButton();
        scrollBar = factory.createScrollBar();
    }

    void renderUI() {
        button.render();
        scrollBar.scroll();
    }

    public static void main(String[] args) {
        UIFactory winFactory = new WindowsFactory();
        Application winApp = new Application(winFactory);
        winApp.renderUI();
    }

}
