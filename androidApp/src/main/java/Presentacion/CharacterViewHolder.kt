package Presentacion

import Modelo.Character
import androidx.recyclerview.widget.RecyclerView
import com.example.marveltptaller.android.databinding.ListItemCharacterBinding
//import com.example.marveltptaller.android.databinding.ListItemCharacterBinding
//import com.example.marveltptaller.android.marvelcharacters.
import com.squareup.picasso.Picasso

class CharacterViewHolder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.name.text = character.name
        binding.description.text = character.description
        if (character.thumbnailUrl.isNotEmpty()) {
            Picasso.get()
                .load(character.thumbnailUrl)
                .into(binding.image)
        } else {
            binding.image.setImageURI(null)
        }
    }

}
