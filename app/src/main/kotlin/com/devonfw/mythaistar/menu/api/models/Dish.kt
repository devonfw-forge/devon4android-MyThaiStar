package com.devonfw.mythaistar.menu.api.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-02-06.
 */
class Dish() :Parcelable {
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
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("imageId")
    @Expose
    var imageId: Int? = null

    

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        modificationCounter = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        description = parcel.readString()
        price = parcel.readValue(Double::class.java.classLoader) as? Double
        imageId = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(modificationCounter)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(price)
        parcel.writeValue(imageId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dish> {
        override fun createFromParcel(parcel: Parcel): Dish {
            return Dish(parcel)
        }

        override fun newArray(size: Int): Array<Dish?> {
            return arrayOfNulls(size)
        }
    }
}