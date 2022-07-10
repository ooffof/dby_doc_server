package top.cuizilin.dby;

import com.alibaba.fastjson.JSON;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDestinationDictionary;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.Test;
import top.cuizilin.dby.utils.PDFUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class NormalTest {
    public static final String name = "D:\\SOFT\\file\\data-dongbeiya\\二十四史中华书局2013 编目全文检索版\\01003.点校本二十四史精装版.史记.三.卷一六至卷二二.表[汉]司马迁撰.中华书局2013.pdf";
    @Test
    public void test() throws Exception {
       PDDocument document = PDDocument.load(new File(name));
       PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage bufferedImage = renderer.renderImage(1);
        ImageIO.write(bufferedImage, "JPEG", new File("D:/test.jpg"));
    }

    @Test
    public void test2() throws IOException {
        System.out.println(MD5Encoder.encode("dsagdsa".getBytes()));
    }


    @Test
    public void reName(){
        String dir = "D:\\SOFT\\file\\data-dongbeiya\\二十四史中华书局2013 编目全文检索版";
        File file = new File(dir);
        for (File listFile : file.listFiles()) {
            String fileName = listFile.getName();
            String newFileName = fileName.substring(fileName.indexOf(".") + 1);
            File newFile = new File(dir + "\\" + newFileName);
            listFile.renameTo(newFile);
        }
    }

    @Test
    public void splitPDF() throws IOException {
        String dir = "D:\\SOFT\\file\\data-dongbeiya\\二十四史中华书局2013 编目全文检索版";
        File file = new File(dir);
        String dest = "D:\\SOFT\\file\\data-dongbeiya\\dest";
        for (File listFile : file.listFiles()) {
            PDDocument doc = new PDDocument();
            PDDocument document = PDDocument.load(listFile);
            for (int i = 0; i < 10; i++){
                PDPage pdPage = document.getPage(i);
                doc.addPage(pdPage);
            }
            File out = new File(dest + "\\" + listFile.getName());
            doc.save(out);
        }
    }

    @Test
    public void stringTest(){
        String s = "[{\"children\":[],\"name\":\"封面\",\"number\":1},{\"children\":[],\"name\":\"書名\",\"number\":2},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"侯顯\",\"number\":7}],\"name\":\"鄭和\",\"number\":4},{\"children\":[{\"children\":[],\"name\":\"興安\",\"number\":7},{\"children\":[],\"name\":\"范弘\",\"number\":9},{\"children\":[],\"name\":\"王瑾\",\"number\":9},{\"children\":[],\"name\":\"阮安\",\"number\":9},{\"children\":[],\"name\":\"阮浪\",\"number\":9}],\"name\":\"金英\",\"number\":7},{\"children\":[],\"name\":\"王振\",\"number\":10},{\"children\":[{\"children\":[],\"name\":\"亦失哈\",\"number\":14},{\"children\":[],\"name\":\"韋力轉\",\"number\":14},{\"children\":[],\"name\":\"劉永誠\",\"number\":14}],\"name\":\"曹吉祥\",\"number\":12},{\"children\":[{\"children\":[],\"name\":\"覃吉\",\"number\":16}],\"name\":\"懷恩\",\"number\":15},{\"children\":[{\"children\":[],\"name\":\"尚銘\",\"number\":19}],\"name\":\"汪直\",\"number\":16},{\"children\":[{\"children\":[],\"name\":\"錢能\",\"number\":20},{\"children\":[],\"name\":\"韋眷\",\"number\":21},{\"children\":[],\"name\":\"王敬\",\"number\":21}],\"name\":\"梁芳\",\"number\":19},{\"children\":[{\"children\":[],\"name\":\"鄧原\",\"number\":22},{\"children\":[],\"name\":\"麥秀\",\"number\":22},{\"children\":[],\"name\":\"藍忠\",\"number\":22},{\"children\":[],\"name\":\"劉淸\",\"number\":22},{\"children\":[],\"name\":\"蕭敬\",\"number\":22}],\"name\":\"何鼎\",\"number\":21},{\"children\":[],\"name\":\"李廣\",\"number\":22},{\"children\":[],\"name\":\"蔣琮\",\"number\":23},{\"children\":[],\"name\":\"劉瑾\",\"number\":24},{\"children\":[],\"name\":\"張永\",\"number\":30},{\"children\":[{\"children\":[],\"name\":\"魏彬\",\"number\":32},{\"children\":[],\"name\":\"張忠\",\"number\":33},{\"children\":[],\"name\":\"吳經\",\"number\":33},{\"children\":[],\"name\":\"劉允\",\"number\":33}],\"name\":\"谷大用\",\"number\":32}],\"name\":\"宦官一\",\"number\":3}],\"name\":\"明史卷三百四 列傳第一百九十二\",\"number\":3},{\"children\":[{\"children\":[{\"children\":[],\"name\":\"李芳\",\"number\":37},{\"children\":[],\"name\":\"馮保\",\"number\":38},{\"children\":[],\"name\":\"張鯨\",\"number\":42},{\"children\":[{\"children\":[],\"name\":\"陳奉\",\"number\":44},{\"children\":[],\"name\":\"高准\",\"number\":46}],\"name\":\"陳增\",\"number\":43},{\"children\":[],\"name\":\"糜永\",\"number\":47},{\"children\":[],\"name\":\"陳矩\",\"number\":51},{\"children\":[],\"name\":\"王安\",\"number\":53},{\"children\":[],\"name\":\"魏忠賢\",\"number\":54},{\"children\":[{\"children\":[],\"name\":\"李永貞\",\"number\":63},{\"children\":[],\"name\":\"涂文輔\",\"number\":64},{\"children\":[],\"name\":\"劉若愚\",\"number\":64}],\"name\":\"王體乾\",\"number\":63},{\"children\":[],\"name\":\"崔文昇\",\"number\":65},{\"children\":[],\"name\":\"張彝憲\",\"number\":65},{\"children\":[],\"name\":\"高起潛\",\"number\":67},{\"children\":[],\"name\":\"王承恩\",\"number\":68},{\"children\":[],\"name\":\"方正化\",\"number\":69}],\"name\":\"宦官二\",\"number\":37}],\"name\":\"明史卷三百五 列傳第一百九十三\",\"number\":37},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"劉宇\",\"number\":75},{\"children\":[],\"name\":\"曹元\",\"number\":76}],\"name\":\"焦芳\",\"number\":72},{\"children\":[{\"children\":[],\"name\":\"韓福\",\"number\":79},{\"children\":[],\"name\":\"李憲\",\"number\":80},{\"children\":[],\"name\":\"張龍\",\"number\":81}],\"name\":\"張綵\",\"number\":78},{\"children\":[{\"children\":[],\"name\":\"魏廣微\",\"number\":81},{\"children\":[],\"name\":\"黃立極\",\"number\":84},{\"children\":[],\"name\":\"施鳳來\",\"number\":84},{\"children\":[],\"name\":\"張瑞圖\",\"number\":84},{\"children\":[],\"name\":\"來宗道\",\"number\":85},{\"children\":[],\"name\":\"楊景辰\",\"number\":85}],\"name\":\"顧秉謙\",\"number\":81},{\"children\":[{\"children\":[],\"name\":\"吳淳夫\",\"number\":88},{\"children\":[],\"name\":\"倪文煥\",\"number\":88},{\"children\":[],\"name\":\"田吉\",\"number\":89},{\"children\":[],\"name\":\"李夔龍\",\"number\":89}],\"name\":\"崔呈秀\",\"number\":86},{\"children\":[{\"children\":[],\"name\":\"梁夢環\",\"number\":93},{\"children\":[],\"name\":\"劉詔\",\"number\":93},{\"children\":[],\"name\":\"邵輔忠\",\"number\":94},{\"children\":[],\"name\":\"孫杰\",\"number\":94}],\"name\":\"劉志選\",\"number\":91},{\"children\":[{\"children\":[],\"name\":\"石三畏\",\"number\":95},{\"children\":[],\"name\":\"張訥\",\"number\":96},{\"children\":[],\"name\":\"盧承欽\",\"number\":97},{\"children\":[],\"name\":\"門克新\",\"number\":97},{\"children\":[],\"name\":\"劉徽\",\"number\":97},{\"children\":[],\"name\":\"智铤\",\"number\":98}],\"name\":\"曹欽程\",\"number\":94},{\"children\":[{\"children\":[],\"name\":\"周應秋\",\"number\":100},{\"children\":[],\"name\":\"弟 維持\",\"number\":100}],\"name\":\"王紹徽\",\"number\":98},{\"children\":[{\"children\":[],\"name\":\"徐大化\",\"number\":103},{\"children\":[],\"name\":\"李蕃\",\"number\":103},{\"children\":[],\"name\":\"李魯生\",\"number\":104},{\"children\":[],\"name\":\"李恒茂\",\"number\":104}],\"name\":\"霍維華\",\"number\":100},{\"children\":[],\"name\":\"閻鳴泰\",\"number\":105},{\"children\":[],\"name\":\"賈繼春\",\"number\":108},{\"children\":[{\"children\":[],\"name\":\"許顯純\",\"number\":110},{\"children\":[],\"name\":\"崔應元\",\"number\":111},{\"children\":[],\"name\":\"楊寰\",\"number\":111},{\"children\":[],\"name\":\"孫雲鶴\",\"number\":111}],\"name\":\"田爾耕\",\"number\":110}],\"name\":\"閹黨\",\"number\":71}],\"name\":\"明史卷三百六 列傳一百九十四\",\"number\":71},{\"children\":[{\"children\":[{\"children\":[],\"name\":\"紀綱\",\"number\":114},{\"children\":[{\"children\":[],\"name\":\"逯杲\",\"number\":116}],\"name\":\"門達\",\"number\":115},{\"children\":[],\"name\":\"李孜省\",\"number\":119},{\"children\":[],\"name\":\"繼曉\",\"number\":122},{\"children\":[{\"children\":[],\"name\":\"許泰\",\"number\":128}],\"name\":\"江彬\",\"number\":123},{\"children\":[],\"name\":\"錢寧\",\"number\":128},{\"children\":[],\"name\":\"陸炳\",\"number\":130},{\"children\":[],\"name\":\"邵元節\",\"number\":132},{\"children\":[{\"children\":[],\"name\":\"段朝用\",\"number\":136},{\"children\":[],\"name\":\"龔可佩\",\"number\":136},{\"children\":[],\"name\":\"藍道行\",\"number\":137},{\"children\":[],\"name\":\"胡大順\",\"number\":137},{\"children\":[],\"name\":\"田玉\",\"number\":138},{\"children\":[],\"name\":\"王金\",\"number\":138}],\"name\":\"陶仲文\",\"number\":134},{\"children\":[{\"children\":[],\"name\":\"盛端明\",\"number\":141},{\"children\":[],\"name\":\"朱隆禧\",\"number\":141},{\"children\":[],\"name\":\"姜儆\",\"number\":141},{\"children\":[],\"name\":\"王大任\",\"number\":142}],\"name\":\"顧可學\",\"number\":140}],\"name\":\"佞倖\",\"number\":113}],\"name\":\"明史卷三百七 列傳第一百九十五\",\"number\":113},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"陳寧\",\"number\":146}],\"name\":\"胡惟庸\",\"number\":144},{\"children\":[{\"children\":[],\"name\":\"馬麟\",\"number\":150},{\"children\":[],\"name\":\"丁珏\",\"number\":151},{\"children\":[],\"name\":\"秦政學\",\"number\":151},{\"children\":[],\"name\":\"趙緯\",\"number\":151},{\"children\":[],\"name\":\"李芳\",\"number\":152}],\"name\":\"陳瑛\",\"number\":148},{\"children\":[{\"children\":[],\"name\":\"子 世蕃\",\"number\":158},{\"children\":[],\"name\":\"趙文華\",\"number\":159},{\"children\":[],\"name\":\"鄢懋卿\",\"number\":162}],\"name\":\"嚴嵩\",\"number\":152},{\"children\":[],\"name\":\"周延儒\",\"number\":163},{\"children\":[],\"name\":\"溫體仁\",\"number\":169},{\"children\":[{\"children\":[],\"name\":\"阮大鋮\",\"number\":175}],\"name\":\"馬士英\",\"number\":175}],\"name\":\"奸臣\",\"number\":143}],\"name\":\"明史卷三百八 列傳第一百九十六\",\"number\":143},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"高迎祥\",\"number\":190},{\"children\":[],\"name\":\"劉宗敏\",\"number\":194},{\"children\":[],\"name\":\"李巖\",\"number\":194},{\"children\":[],\"name\":\"牛金星\",\"number\":194},{\"children\":[],\"name\":\"羅汝才\",\"number\":197}],\"name\":\"李自成\",\"number\":186},{\"children\":[],\"name\":\"張獻忠\",\"number\":207}],\"name\":\"流賊\",\"number\":185}],\"name\":\"明史卷三百九 列傳第一百九十七\",\"number\":185},{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"施南宣撫司\",\"number\":223},{\"children\":[],\"name\":\"散毛宣撫司\",\"number\":223},{\"children\":[],\"name\":\"忠建宣撫司\",\"number\":223},{\"children\":[],\"name\":\"容美宣撫司\",\"number\":225}],\"name\":\"施州\",\"number\":222},{\"children\":[],\"name\":\"永順軍民宣慰使司\",\"number\":229},{\"children\":[],\"name\":\"靖州軍民宣慰使司\",\"number\":233}],\"name\":\"湖廣土司\",\"number\":220}],\"name\":\"土司一\",\"number\":219}],\"name\":\"明史卷三百十 列傳第一百九十八\",\"number\":219},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"烏蒙烏撒東川鎭雄四軍民府\",\"number\":239},{\"children\":[],\"name\":\"馬湖\",\"number\":253},{\"children\":[{\"children\":[],\"name\":\"寧番衞\",\"number\":257},{\"children\":[],\"name\":\"越巂衞\",\"number\":258},{\"children\":[],\"name\":\"鹽井衞\",\"number\":258},{\"children\":[],\"name\":\"會川衞\",\"number\":259}],\"name\":\"建昌衞\",\"number\":254},{\"children\":[],\"name\":\"茂州衞\",\"number\":260},{\"children\":[],\"name\":\"松潘衞\",\"number\":262},{\"children\":[],\"name\":\"天全六番招討司\",\"number\":269},{\"children\":[],\"name\":\"黎州安撫司\",\"number\":271}],\"name\":\"四川土司一\",\"number\":239}],\"name\":\"土司二\",\"number\":239}],\"name\":\"明史卷三百十一 列傳第一百九十九\",\"number\":239},{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"name\":\"播州宣慰司\",\"number\":277},{\"children\":[],\"name\":\"永寧宣撫司\",\"number\":287},{\"children\":[],\"name\":\"酉陽宣撫司\",\"number\":295},{\"children\":[],\"name\":\"石砫宣撫司\",\"number\":297}],\"name\":\"四川土司二\",\"number\":277}],\"name\":\"土司三\",\"number\":277}],\"name\":\"明史卷三百十二 列傳第二百\",\"number\":277},{\"children\":[],\"name\":\"封底\",\"number\":301}]";
        System.out.println(JSON.toJSONString(s));

    }
}
