package top.cuizilin.dby.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.catalina.util.ParameterMap;
import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.pojo.Tag;
import top.cuizilin.dby.mapper.TagMapper;
import top.cuizilin.dby.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.dby.utils.InfoUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Override
    public R<Tag> add(String tagName) {
        if(this.getOne(new QueryWrapper<Tag>().eq("name", tagName)) != null){
            return R.normalError("该标签已经存在");
        }
       Tag tag = new Tag();
       tag.setName(tagName);
       this.save(tag);
       return R.normalSuccess();
    }

    @Override
    public R<List<Tag>> listTag() {
        return R.normalSuccessAndData(this.list());
    }

    @Override
    public R<String> getInfo(Boolean flag, String info) throws IOException {
       if(flag) {
           //write
           return R.normalSuccessAndData(InfoUtil.writeJson(info));
       }else {
           //read
           return R.normalSuccessAndData(InfoUtil.readJson());
       }
    }
}
