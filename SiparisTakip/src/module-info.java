module siparistakip {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens siparistakip to javafx.graphics, javafx.fxml, javafx.base;
}
