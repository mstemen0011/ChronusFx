module your.module.name {
    requires javafx.controls;
    requires javafx.fxml;
    // Add other required JavaFX modules
    opens chronusfx; // Open your package to JavaFX
}