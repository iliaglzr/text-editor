package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
                textEditor.currentFile = selectedFile;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = selectedFile;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
