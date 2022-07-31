package com.example.databinding.field;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.databinding.BR;

/**
 * DataBinding会设置一个listener在绑定的对象上，以便监听对象字段的变动
 * 只需要在getter上使用 Bindable 注解，并在setter中通知更新即可
 */
public class User extends BaseObservable {
    private String name;
    private String englishName;
    private String address;
    private String height;
    private String weight;
    private String url;
    private boolean flag;

    public User(String name, String englishName, String address, String height) {
        this.name = name;
        this.englishName = englishName;
        this.address = address;
        this.height = height;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
        notifyPropertyChanged(BR.englishName);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
        notifyPropertyChanged(BR.height);  //只更新本字段
    }

    @Bindable
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
        notifyPropertyChanged(BR.weight);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        notifyPropertyChanged(BR.flag);
//        notifyChange();  //更新所有字段
    }
}
