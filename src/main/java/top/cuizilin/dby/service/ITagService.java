package top.cuizilin.dby.service;

import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
public interface ITagService extends IService<Tag> {

    R<Tag> add(String tagName);

    R<List<Tag>> listTag();

    R<String> getInfo(Boolean flag, String info) throws IOException;
}
