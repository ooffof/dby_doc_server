package top.cuizilin.dby.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import top.cuizilin.dby.dto.*;
import top.cuizilin.dby.service.IBookService;
import top.cuizilin.dby.service.IBooklistService;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@RestController
@Validated
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBooklistService booklistService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @Validated BookUpload bookUpload) throws Exception {
        return JSON.toJSONString(bookService.upload(file, bookUpload));
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return JSON.toJSONString(bookService.listBook(pageNo, pageSize));
    }

    @GetMapping(value = "/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getCoverByBookId(String bookId) throws Exception {
        return bookService.getCoverByBookId(bookId);
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] download(String bookId) {
        byte[] b = null;
        try {
            b = bookService.downloadBook(bookId);
        } catch (Exception e) {

        }
        return b;
    }


    @PostMapping("/booklist/add")
    public String addBookToBooklist(@RequestBody @Validated BookAdd bookAdd) {
        return JSON.toJSONString(booklistService.addBook(bookAdd));
    }

    @GetMapping("/booklist/getAll")
    public String getAllBook(@NotNull(message = "书单id不能为空") String listId) {
        return JSON.toJSONString(booklistService.getAll(listId));
    }

    @PostMapping("/booklist/deleteBook")
    public String deleteBookFromBooklist(@RequestBody @Validated BookDelete bookDelete) {
        return JSON.toJSONString(booklistService.deleteBookFromBooklist(bookDelete));
    }

    @GetMapping("/getById")
    public String getBookByBookId(@NotNull(message = "bookId不能为空") String bookId) {
        return JSON.toJSONString(bookService.getBookByBookId(bookId));
    }

    @GetMapping("/searchByName")
    public String searchBookByName(@NotNull(message = "书名不能为空") String bookName,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return JSON.toJSONString(bookService.searchBookByName(bookName, pageNo, pageSize));
    }

    @GetMapping("/recommend")
    public String getRecommendBooks(@NotNull(message = "bookId不能为空") String bookId) {
        return JSON.toJSONString(bookService.getRecommendBooks(bookId));
    }

    @GetMapping("/recommendList")
    public String getRecommendBooklist(@NotNull(message = "bookId不能为空") String bookId) {
        return JSON.toJSONString(bookService.getRecommendBookList(bookId));
    }

    @GetMapping(value = "/single", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getSinglePDF(@NotNull(message = "bookId不能为空") String bookId, @NotNull(message = "pageNo不能为空") Integer pageNo) throws IOException {
        return bookService.getSinglePDF(bookId, pageNo);
    }

    @GetMapping("/booklist/all")
    public String getAllBooklist() {
        return JSON.toJSONString(booklistService.getAllBooklist(), SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping("/booklist/delete")
    public String deleteBooklist(@NotNull(message = "bookId不能为空") String booklistId) {
        return JSON.toJSONString(booklistService.deleteById(booklistId));
    }

    @GetMapping("/delete")
    public String deleteBook(@NotNull(message = "bookId不能为空") String bookId) {
        return JSON.toJSONString(bookService.deleteById(bookId));
    }

    @PostMapping("/update")
    public String updateBook(@RequestBody @Validated BookUpdate bookUpdate) {
        return JSON.toJSONString(bookService.updateBook(bookUpdate));
    }

    @GetMapping("/count")
    public String countBook() {
        return JSON.toJSONString(bookService.countBook());
    }

    @GetMapping("/booklist/count")
    public String countBooklist() {
        return JSON.toJSONString(booklistService.countBooklist());
    }


    @GetMapping("/searchByTagName")
    public String searchByTagName(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                  String tagName){
        return JSON.toJSONString(bookService.searchByTagName(pageNo, pageSize, tagName));
    }

    @GetMapping("/searchByTypeName")
    public String searchByTypeName(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                   String typeName){
        return JSON.toJSONString(bookService.searchByTypeName(pageNo, pageSize, typeName));
    }

}

