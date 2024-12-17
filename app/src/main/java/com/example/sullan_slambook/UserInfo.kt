    package com.example.sullan_slambook.model

    import android.os.Parcel
    import android.os.Parcelable



    data class UserInfo(
        val username: String? = "",
        val password: String? = "",
        val fullName: String? = "",
        val gender: String? = "",
        val address: String? = "",
      //  val age: Int = 0,
        val number: String? = "",
        val gmail: String? = "",
        val birthDate: String? = "",

        val color: String? = "",
        val place: String? = "",
        val fruit: String? = "",
        val food: String? = "",
        val song: String? = "",
        val movie: String? = "",
        val artist: String? = "",

        val favoriteHobby: String? = "",
        val wayToRelax: String? = "",
        val essentialHobby: String? = "",
        val happiestMoment: String? = "",
        val dailyInspiration: String? = "",

        val peace: String? = "",
        val joy: String? = "",
        val angerTrigger: String? = "",
        val sadnessEmbrace: String? = "",
        val biggestDream: String? = "",
        val feelSafe: String? = "",

        val biggestLifeChange: String? = "",
        val challengingMoment: String? = "",
        val meaningfulExperience: String? = "",
        val recentSelfDiscovery: String? = "",
        val motivationSource: String? = "",
        val fear: String? = "",
        val proudestMoment: String? = "",
        val experiencedSex: String? = "",
        val firstSexualPartner: String? = "",
        val firstGirlfriend: String? = "",
        val firstBoyfriend: String? = "",
        val mostMemorableRelationship: String? = "",
        val firstLove: String? = "",
        val selfDiscovery: String? = "",
        val motivation: String? = ""
    ) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
       //     parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(username)
            parcel.writeString(password)
            parcel.writeString(fullName)
            parcel.writeString(gender)
            parcel.writeString(address)
          //  parcel.writeInt(age)
            parcel.writeString(number)
            parcel.writeString(gmail)
            parcel.writeString(birthDate)
            parcel.writeString(color)
            parcel.writeString(place)
            parcel.writeString(fruit)
            parcel.writeString(food)
            parcel.writeString(song)
            parcel.writeString(movie)
            parcel.writeString(artist)
            parcel.writeString(favoriteHobby)
            parcel.writeString(wayToRelax)
            parcel.writeString(essentialHobby)
            parcel.writeString(happiestMoment)
            parcel.writeString(dailyInspiration)
            parcel.writeString(peace)
            parcel.writeString(joy)
            parcel.writeString(angerTrigger)
            parcel.writeString(sadnessEmbrace)
            parcel.writeString(biggestDream)
            parcel.writeString(feelSafe)
            parcel.writeString(biggestLifeChange)
            parcel.writeString(challengingMoment)
            parcel.writeString(meaningfulExperience)
            parcel.writeString(recentSelfDiscovery)
            parcel.writeString(motivationSource)
            parcel.writeString(fear)
            parcel.writeString(proudestMoment)
            parcel.writeString(experiencedSex)
            parcel.writeString(firstSexualPartner)
            parcel.writeString(firstGirlfriend)
            parcel.writeString(firstBoyfriend)
            parcel.writeString(mostMemorableRelationship)
            parcel.writeString(firstLove)
            parcel.writeString(selfDiscovery)
            parcel.writeString(motivation)
        }

        override fun describeContents(): Int = 0



        companion object CREATOR : Parcelable.Creator<UserInfo> {
            override fun createFromParcel(parcel: Parcel): UserInfo {
                return UserInfo(parcel)
            }

            override fun newArray(size: Int): Array<UserInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
