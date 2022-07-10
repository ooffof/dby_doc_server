package top.cuizilin.dby.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionThread;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PDFUtil {


    static {
        final String SYSTEM = System.getProperty("os.name")
                .toLowerCase().indexOf("windows") >= 0 ? "windows" : "linux";
        final String WINDOWS_DIR = "C:/pdf/";

        final String LINUX_DIR = "/usr/local/dby/pdf/";

        final String PATH = SYSTEM == "windows" ? WINDOWS_DIR : LINUX_DIR;

        File file = new File(PATH);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        file = new File(PATH + "cover/");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    static final String SYSTEM = System.getProperty("os.name")
            .toLowerCase().indexOf("windows") >= 0 ? "windows" : "linux";
    static final String WINDOWS_DIR = "C:/pdf/";

    static final String LINUX_DIR = "/usr/local/dby/pdf/";

    public static final String PATH = ("windows".equals(SYSTEM) ? WINDOWS_DIR : LINUX_DIR);

    public static byte[] getPDF(String src) throws IOException {
        FileInputStream in = new FileInputStream(src);
        byte[] b = new byte[in.available()];
        in.read(b);
        return b;
    }

    public static String saveFile(byte[] b, String name) throws IOException {
        FileOutputStream out = new FileOutputStream(PATH + name);
        out.write(b);
        return PATH + name;
    }

    public static String saveCover(PDDocument pdDocument, String name) throws Exception {
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        BufferedImage bufferedImage = renderer.renderImage(0);
        String coverPath = PATH + "cover/" + name.substring(0, name.indexOf(".pdf")) + ".jpg";
        ImageIO.write(bufferedImage, "JPEG", new File(coverPath));
        return coverPath;
    }

    public static void deleteBookAndCover(String bookPath, String coverPath){
        File book = new File(bookPath);
        book.delete();
        File bookCover = new File(coverPath);
        bookCover.delete();
    }

    public static Map<String, Object> getBookMarkAndSaveCover(byte[] b, String name) throws Exception {
        PDDocument document = PDDocument.load(b);

        try {
            saveCover(document, name);
        }catch (Exception e){

        }

        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", document.getNumberOfPages());

        PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
        List<DirInfo> infoList = new ArrayList<>();
        if (outline == null)
            return map;
        else
            printBookmark(document, outline, "", infoList);
        map.put("dir", infoList);
        return map;
    }


    public static Map<String, Object> processPDF(byte[] b, String name) throws Exception {
        String src = saveFile(b, name);

        PDDocument document = PDDocument.load(b);
        String imgSrc = saveCover(document, name);

        Map<String, Object> map = new HashMap<>();
        map.put("src", src);
        map.put("imgSrc", imgSrc);
        map.put("totalPage", document.getNumberOfPages());

        PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
        List<DirInfo> infoList = new ArrayList<>();
        if (outline == null)
            return map;
        else
            printBookmark(document, outline, "", infoList);
        map.put("dir", infoList);
        return map;
    }

    public static Map<String, Object> processPDF(MultipartFile file) throws Exception {
       return processPDF(file.getBytes(), file.getOriginalFilename());
    }

    static class DirInfo{
        private String name;
        private Integer number;
        private List<DirInfo> children;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public List<DirInfo> getChildren() {
            return children;
        }

        public void setChildren(List<DirInfo> children) {
            this.children = children;
        }
    }

    public static void printBookmark(PDDocument document,
                                     PDOutlineNode bookmark,
                                     String indentation,
                                     List<DirInfo> dirInfos) throws IOException {
        PDOutlineItem current = bookmark.getFirstChild();
        while (current != null) {
            // one could also use current.findDestinationPage(document) to get the page number,
            // but this example does it the hard way to explain the different types
            // Note that bookmarks can also do completely different things, e.g. link to a website,
            // or to an external file. This example focuses on internal pages.
            int pageNo = 0;
            if (current.getDestination() instanceof PDPageDestination) {
                PDPageDestination pd = (PDPageDestination) current.getDestination();
                pageNo = pd.retrievePageNumber() + 1;
            } else if (current.getDestination() instanceof PDNamedDestination) {
                PDPageDestination pd = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) current.getDestination());
                if (pd != null) {
                    pageNo = pd.retrievePageNumber() + 1;
                }
            } else if (current.getDestination() != null) {
                System.out.println(indentation + "Destination class: " + current.getDestination().getClass().getSimpleName());
            }

            if (current.getAction() instanceof PDActionGoTo) {
                PDActionGoTo gta = (PDActionGoTo) current.getAction();
                if (gta.getDestination() instanceof PDPageDestination) {
                    PDPageDestination pd = (PDPageDestination) gta.getDestination();
                    pageNo = pd.retrievePageNumber() + 1;
                } else if (gta.getDestination() instanceof PDNamedDestination) {
                    PDPageDestination pd = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) gta.getDestination());
                    if (pd != null) {
                        pageNo = pd.retrievePageNumber() + 1;
                    }
                } else {
                    System.out.println(indentation + "Destination class: " + gta.getDestination().getClass().getSimpleName());
                }
            } else if (current.getAction() != null) {
                System.out.println(indentation + "Action class: " + current.getAction().getClass().getSimpleName());
            }
            DirInfo info = new PDFUtil.DirInfo();
            info.setName(current.getTitle().trim());
            info.setNumber(pageNo);
            List<DirInfo> children = new ArrayList<>();
            info.setChildren(children);
            dirInfos.add(info);
            //System.out.println(indentation + current.getTitle().trim() + "==========>" + pageNo);
            printBookmark(document, current, indentation + "    ", children);
            current = current.getNextSibling();
        }
    }

//    public static byte[] getSinglePDF(String bookId, String src, Integer pageNo) throws IOException {
//        if(redisTemplate.opsForValue().get(bookId) == null){
//           byte[] b = getPDF(src);
//           redisTemplate.opsForValue().set(bookId, b);
//        }
//        byte[] nb = (byte[]) redisTemplate.opsForValue().get(bookId);
//        PDDocument document = PDDocument.load(nb);
//        PDDocument returnDoc = new PDDocument();
//        returnDoc.addPage(document.getPage(pageNo));
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        returnDoc.save(out);
//        return out.toByteArray();
//    }



}
