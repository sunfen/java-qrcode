package cn.sf.qrcode.document.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.sf.qrcode.document.domain.DocumentVO;
import cn.sf.qrcode.document.domain.entity.Document;


/**
 * @author ztw
 * @date 2018/12/29
 */
 @Service
public class DocumentConvertUtil {

     @Value("${qrcode.url}")
     private String Base_Path;

     @Value("${qrcode.file.upload-path}")
     private String FILE_Path;
     
     public DocumentVO convertDocument(Document document) {

         if (document == null) {
            return null;
         }
        
         DocumentVO vo = new DocumentVO();
        
        vo.setId(document.getId());
        vo.setName(document.getName());
        vo.setObjectId(document.getObjectId());
        
        final Path path = Paths.get( FILE_Path);
        final String baseString = path.toUri().toString();
        final Set<String > attrViews = path.getFileSystem().supportedFileAttributeViews();
        
        
        if (!attrViews.contains("posix")) {

            final String windowPath = FILE_Path.substring(2, FILE_Path.length());
            
            vo.setPath(Base_Path + document.getPathName().replaceAll(baseString, windowPath + "/"));
            
            if (document.getMinPath() != null) {
                vo.setMinPath(Base_Path + document.getMinPath().replaceAll(baseString, windowPath+ "/"));
            }
        } else {
            
            vo.setPath(Base_Path + document.getPathName().replaceAll(baseString, "/files/"));
            
            if (document.getMinPath() != null) {
               vo.setMinPath(Base_Path + document.getMinPath().replaceAll(baseString, "/files/"));
            }
        }
        
        return vo;
     }
     
     
     public List<DocumentVO> convertDocument(final List<Document> sources) {
         List<DocumentVO> list = new ArrayList<>();
         for(final Document source : sources){
             list.add(this.convertDocument(source));
         }
         return list;
     }
     
}
