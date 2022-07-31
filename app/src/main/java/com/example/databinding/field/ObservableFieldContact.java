package com.example.databinding.field;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * 创建Observable类还是需要花费一点时间的，如果想要省时，或者数据类的字段很少的话，可以使用 ObservableField
 * 以及它的派生 ObservableBoolean、ObservableByte 、ObservableChar、ObservableShort、ObservableInt、ObservableLong、
 * ObservableFloat、ObservableDouble、ObservableParcelable
 */
public class ObservableFieldContact {
    public ObservableField<String> mName = new ObservableField<>();
    public ObservableField<String> mPhone = new ObservableField<>();
    public ObservableInt mType = new ObservableInt();

    public ObservableFieldContact(String name, String phone, int type) {
        mName.set(name);
        mPhone.set(phone);
        mType.set(type);
    }
}
