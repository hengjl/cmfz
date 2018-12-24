package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class PoiController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterMapper chapterMapper;

    @RequestMapping("/exportData")
    public void exportData(HttpSession session, HttpServletResponse response) {


        List<Album> albumList = albumService.queryAllAlbum();
        for (int i = 0; i < albumList.size(); i++) {

            String musicUrl = albumList.get(i).getCoverImg();
            String musicUrl2 = session.getServletContext().getRealPath("/") + musicUrl;
            albumList.get(i).setCoverImg(musicUrl2);
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("吉祥妙音", "单曲"), Album.class, albumList);
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=aa.xls");
        response.setCharacterEncoding("utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* workbook.write(new FileOutputStream(new File("F:/music.xls")));*/
    }
}







