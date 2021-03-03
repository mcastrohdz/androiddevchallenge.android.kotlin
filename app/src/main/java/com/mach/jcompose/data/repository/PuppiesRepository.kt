package com.mach.jcompose.data.repository

import com.mach.jcompose.R
import com.mach.jcompose.data.model.DogSex
import com.mach.jcompose.data.model.DogSize
import com.mach.jcompose.data.model.Puppy

object PuppiesRepository {

    private val data = listOf(
        Puppy(
            id = 1,
            name = "Radar",
            picture = R.drawable.radar,
            age = "2 yrs",
            size = DogSize.MEDIUM,
            sex = DogSex.GOOD_BOY,
            description = "This handsome guy is Radar! Radar is a very goofy yellow lab who absolutely LOVES his people! He`ll take any chance to say hello to his humans and is a social butterfly. He could spend all day playing outside and adores cuddles and hugs. He should be the only pet in his home and ideally would be best with teens and adults due to his jumpy/mouthy behavior. Radar also cannot go to a home with any cats or small animals and would do best in a home without dogs, though he would like to meet up with doggie friends outside his home."
        ),
        Puppy(
            id = 2,
            name = "Olivia",
            picture = R.drawable.olivia,
            age = "1 yr",
            size = DogSize.MEDIUM,
            sex = DogSex.GOOD_GIRL,
            description = "Olivia is a very shy girl that will need time to come out of her shell. Loud noises while walking do make her nervous so a confident and experienced owner is important. Since Olivia is nervous with new things, she can be reactive with other dogs if they are too overbearing. Since she will need additional training Olivia will do best in a home as an only dog until she is confident enough to understand she is safe. Olivia is decompressing in her foster home and with time, patience and love Olivia will make the perfect companion. She loves her humans and will follow them around the house wherever they go."
        ),
        Puppy(
            id = 3,
            name = "Smith",
            picture = R.drawable.smith,
            age = "8 months",
            size = DogSize.LARGE,
            sex = DogSex.GOOD_GIRL,
            description = "Smith is an 8 month old, 50lb lab/border collie mix from Texas! He was rescued form a shelter and is currently learning how to live life in a home! Smith has had very little structure in his young life and wants nothing but love from his humans! He will need some training with basic commands and he has shown signs of resource guarding his food when other dogs are near. An experienced adopter is needed for Smith as he will need additional time and patience spent with him to work through any challenges he may experience as he continues to grow! Smith loves everyone and will play non-stop, an active household will be best for Smith! Smith does love other dogs and will play all day long. Adopters would need to remain vigilant with toys, treats and food bowls with other dogs until Smith understands that their is enough for everyone! Smith is looking for that perfect home where he can run, play, give unlimited amount of kisses and relax on the couch, could that be with you?!"
        ),
        Puppy(
            id = 4,
            name = "Jelly",
            picture = R.drawable.jelly,
            age = "2 yrs",
            size = DogSize.SMALL,
            sex = DogSex.GOOD_GIRL,
            description = "Jelly is a 2 year old pointer mix that is looking for a very special forever home! She is extremely outgoing and loves to be with her human family at all times! She will play for hours on end and will be sure to keep you laughing. Jelly is a special girl that needs some additional time and attention after being adopted. We are searching for a forever home without children or cats. She has shown fearful reactions to children and it will be best for her to avoid being placed in a home where young children are present. Jelly is housebroken, knows basic commands, food driven and is easily redirected."
        )
    )

    fun getPuppiesList() = data

    fun getPuppyById(puppyId: Int) = data.find { it.id == puppyId }
}