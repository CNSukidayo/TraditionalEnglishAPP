package com.gitee.cnsukidayo.traditionalenglish.handler;

import com.gitee.cnsukidayo.traditionalenglish.entity.WordCategory;

/**
 * @author cnsukidayo
 * @date 2023/1/8 19:48
 */
public interface StartFunctionHandler {
    /**
     * 添加一个单词分类
     *
     * @param wordCategory 单词类别实例对象
     */
    void addNewCategory(WordCategory wordCategory);

    /**
     * 删除一个单词分类
     *
     * @param position 单词分类对象在列表中对应的位置
     */
    void removeCategory(int position);

    /**
     * 得到当前分类列表的长度
     *
     * @return 返回int值
     */
    int categoryListSize();

    /**
     * 根据单词分类在列表中的位置得到对应的WordCategory
     *
     * @param position 单词分类对象在列表中对应的位置
     * @return 返回WordCategory引用
     */
    WordCategory getWordCategoryByPosition(int position);

    /**
     * 根据规则计算出某个WordCategory的标题信息.<br>
     * 该方法的返回值会随着对应的WordCategory内容改变而改变.
     *
     * @param position 单词分类对象在列表中对应的位置
     * @return 返回计算出的标题
     */
    String calculationTitle(int position);

    /**
     * 根据规则计算出某个WordCategory的描述信息.<br>
     * 该方法的返回值会随着对应的WordCategory内容改变而改变.
     *
     * @param position 单词分类对象在列表中对应的位置
     * @return 返回计算出的描述信息
     */
    String calculationDescribe(int position);

    void categoryRemove(int fromPosition, int toPosition);

}
