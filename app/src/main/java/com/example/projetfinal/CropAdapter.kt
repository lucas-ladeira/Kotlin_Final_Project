package com.example.projetfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class CropAdapter(private val context: Context, private var crops: MutableList<Crop>) :
    BaseAdapter() {
    override fun getCount(): Int = crops.size

    override fun getItem(position: Int): Crop = crops[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.crop_list_item, parent, false)
        val crop = getItem(position)

        val imageView = view.findViewById<ImageView>(R.id.img_item)
        val txtViewCropName = view.findViewById<TextView>(R.id.txtViewCropName)
        val txtViewCropType = view.findViewById<TextView>(R.id.txtViewCropType)
        val txtViewCropTemperatures = view.findViewById<TextView>(R.id.txtViewCropTemperatures)
        val txtViewCropHumidities = view.findViewById<TextView>(R.id.txtViewCropHumidities)
        val txtViewCropPlantingDate = view.findViewById<TextView>(R.id.txtViewCropPlantingDate)
        val txtViewCropAddress = view.findViewById<TextView>(R.id.txtViewCropAddress)

        txtViewCropName.text = crop.name
        txtViewCropTemperatures.text =
            "${"%.1f".format(crop.minTemperature)}°C / ${"%.1f".format(crop.maxTemperature)}°C"
        txtViewCropHumidities.text =
            "${"%.1f".format(crop.minHumidity)}% / ${"%.1f".format(crop.maxHumidity)}%"
        txtViewCropPlantingDate.text = crop.plantingDate
        txtViewCropAddress.text = crop.address

        when(crop.type) {
            "Cereal" -> {
                imageView.setImageResource(R.drawable.cereals)
                txtViewCropType.text = context.getString(R.string.cereal_text)
            }
            "Vegetable" -> {
                imageView.setImageResource(R.drawable.vegetables)
                txtViewCropType.text = context.getString(R.string.vegetable_text)
            }
            "Fruit" -> {
                imageView.setImageResource(R.drawable.fruits)
                txtViewCropType.text = context.getString(R.string.fruit_text)
            }
            else -> {
                Toast.makeText(context, "Unknown crop type", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}