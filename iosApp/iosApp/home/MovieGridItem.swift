//
//  MovieGridItem.swift
//  iosApp
//
//  Created by Arun Ajayan on 06/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared


// A sample movie instance for previews
let sampleMovie: [Movie] = [
    Movie(id: 1, title: "Inception", description: "2010", imageUrl: "inception.jpg", releaseDate: "12121"),
    Movie(id: 2, title: "The Dark Knight", description: "2008", imageUrl: "dark_knight.jpg", releaseDate: "12122"),
    Movie(id: 3, title: "Interstellar", description: "2014", imageUrl: "interstellar.jpg", releaseDate: "12123"),
    Movie(id: 4, title: "Dunkirk", description: "2017", imageUrl: "dunkirk.jpg", releaseDate: "12124")
    // Add more movies as needed
]

struct MovieGridItem: View {
    let movie: Movie
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            ZStack{
                
                AsyncImage(url: URL(string: movie.imageUrl)){image in
                    image.resizable()
                }placeholder: {
                    Color.gray
                }
                
                Circle()
                    .frame(width: 50, height: 50)
                    .foregroundColor(.black.opacity(0.7))
                
                Image(systemName: "play.fill")
                
            }
            .frame(maxWidth: .infinity, idealHeight: .infinity)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(movie.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
            
            Text(movie.releaseDate)
                .font(.caption)
            
            
        }
        .frame(maxWidth: .infinity, maxHeight: 260)
    }
}

//struct MovieGridItem_Previews: PreviewProvider {
//    static var previews: some View {
//        MovieGridItem(movie: sampleMovie)
//    }
//}
