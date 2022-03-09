//
//  BreedUIView.swift
//  iosApp
//
//  Created by Seaborn Lee on 2022/3/9.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct BreedUIView: View {
    
    var breed: Breed
    var onFavouriteTapped: (Breed) -> Void = {_ in }
    
    var body: some View {
        VStack{
            KFImage(URL(string: breed.imageUrl))
                .resizable()
                .scaledToFit()
                .cornerRadius(16)
            HStack{
                Text(breed.name)
                    .padding(16)
                Spacer()
                Button(action: { onFavouriteTapped(breed) }, label: {
                    if(breed.isFavourite){
                        Image(systemName: "heart.fill")
                            .resizable()
                            .aspectRatio(1, contentMode: .fit)
                            .frame(width: 24)
                    } else {
                        Image(systemName: "heart")
                            .resizable()
                            .aspectRatio(1, contentMode: .fit)
                            .frame(width: 24)
                    }
                }).padding(16)
                
            }
        }
    }
}

struct BreedUIView_Previews: PreviewProvider {
    static var previews: some View {
        BreedUIView(breed:Breed(name: "beagle", imageUrl:"https://images.dog.ceo//breeds//beagle//n02088364_161.jpg", isFavourite: false))
    }
}

