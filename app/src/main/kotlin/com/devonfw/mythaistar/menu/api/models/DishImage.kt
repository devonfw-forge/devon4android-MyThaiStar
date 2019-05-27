package com.devonfw.mythaistar.menu.api.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DishImage() : Parcelable{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("modificationCounter")
    @Expose
    var modificationCounter: Int? = null
    @SerializedName("revision")
    @Expose
    var revision: Any? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null
    @SerializedName("contentType")
    @Expose
    var contentType: String? = null
    @SerializedName("mimeType")
    @Expose
    var mimeType: String? = null
    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        modificationCounter = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        content = parcel.readString()
        contentType = parcel.readString()
        mimeType = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<DishImage> {

        override fun createFromParcel(parcel: Parcel): DishImage {
            return DishImage(parcel)
        }
        override fun newArray(size: Int): Array<DishImage?> {
            return arrayOfNulls(size)
        }

    }
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        val dishImage = this
        dest?.writeParcelable(dishImage, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

}
