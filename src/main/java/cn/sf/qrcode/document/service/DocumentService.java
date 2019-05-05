package cn.sf.qrcode.document.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.sf.qrcode.document.domain.DocumentVO;


/**
 * @author ztw
 * @date 2018/12/29
 */
public interface DocumentService {

    /**
     * 增加一个新文件
     * @param inputStream 文件流
     * @param fileName 文件名称
     * @param analysis 解析文件
     * @param objectId 业务ID
     * @return
     * @throws IOException
     */
    DocumentVO insert(InputStream inputStream, String fileName, Long objectId) throws IOException ;

    /**
     *根据uuid删除document单个文件 
     * @param id
     */
   void deleteDocumentById(Long id);

    /**
    * 根据类型和id进行删除
    * @param objectId
    * @throws IOException
    */
    void deleteDocumentByObjectId(Long objectId) throws IOException ;
    /**
     * 查找
     * @param objectId
     * @return
     */
    List<DocumentVO> allDocumentByObjectId(Long objectId);

    /**
     * @param removeImages
     * @throws IOException 
     */
    void deleteDocumentByIds(List<String> removeImages) throws IOException;
    
}
