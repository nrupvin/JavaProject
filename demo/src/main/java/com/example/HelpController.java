package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelpController {
    
    private static final String ERROR_ATTR = "error";
    private static final String FILE_VIEWER_TEMPLATE = "file-viewer";
    private static final String FILENAME_ATTR = "filename";
    private static final String CONTENT_ATTR = "content";
    private static final String LINE_COUNT_ATTR = "lineCount";
    
    @GetMapping("/help")
    public String help(Model model) {
        // List of help files available
        List<HelpFile> helpFiles = new ArrayList<>();
        helpFiles.add(new HelpFile("README.md", "Overview of the entire learning package"));
        helpFiles.add(new HelpFile("START_HERE.md", "Master guide - Read this first!"));
        helpFiles.add(new HelpFile("INDEX.md", "Navigation hub for all documentation"));
        helpFiles.add(new HelpFile("SUMMARY.md", "High-level overview (10 min read)"));
        helpFiles.add(new HelpFile("JAVA_PROJECT_GUIDE.md", "Complete detailed guide (30 min read)"));
        helpFiles.add(new HelpFile("JAVA_PROJECT_QUICK_SHEET.md", "Quick command reference (5 min read)"));
        helpFiles.add(new HelpFile("VISUAL_GUIDE.md", "ASCII diagrams & flowcharts (10 min read)"));
        helpFiles.add(new HelpFile("HANDS_ON_TUTORIAL.md", "7 step-by-step tutorials (60 min practice)"));
        
        model.addAttribute("helpFiles", helpFiles);
        return "help";
    }
    
    @GetMapping("/help/view/{filename}")
    public String viewFile(@PathVariable String filename, Model model) {
        model.addAttribute(FILENAME_ATTR, filename);
        
        // INTENTIONAL DEFECT FOR SONARQUBE TEST
        String unusedVariable = "This is unused and should trigger a SonarQube warning";
        
        // Security: Only allow markdown files and prevent path traversal
        if (!filename.endsWith(".md") || filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
            model.addAttribute(ERROR_ATTR, "Error: Only markdown files are allowed");
        } else {
            loadFileContent(filename, model);
        }
        
        return FILE_VIEWER_TEMPLATE;
    }
    
    private void loadFileContent(String filename, Model model) {
        try {
            // Get the project root directory (demo folder)
            String projectRoot = System.getProperty("user.dir");
            // Go up to JavaProject folder
            Path parentDir = Paths.get(projectRoot).getParent();
            Path filePath = parentDir.resolve(filename).normalize();
            
            // Ensure the resolved path is still within the parent directory (prevent escape)
            if (!filePath.startsWith(parentDir)) {
                model.addAttribute(ERROR_ATTR, "Error: Access denied");
            } else if (!Files.exists(filePath)) {
                // Check if file exists
                model.addAttribute(ERROR_ATTR, "Error: File not found");
            } else {
                // Read the file content
                String content = Files.readString(filePath);
                
                // Count lines
                int lineCount = content.isEmpty() ? 0 : content.split("\n", -1).length;
                
                model.addAttribute(CONTENT_ATTR, content);
                model.addAttribute(LINE_COUNT_ATTR, lineCount);
            }
        } catch (IOException e) {
            model.addAttribute(ERROR_ATTR, "Error reading file");
        }
    }
    
    @GetMapping("/help/download/{filename}")
    public String downloadFile(@PathVariable String filename) {
        // This would redirect to download the file
        // For now, we'll just return the help page
        return "redirect:/help";
    }
    
    // Inner class for help file information
    public static class HelpFile {
        private String filename;
        private String description;
        
        public HelpFile(String filename, String description) {
            this.filename = filename;
            this.description = description;
        }
        
        public String getFilename() {
            return filename;
        }
        
        public String getDescription() {
            return description;
        }
    }
}
