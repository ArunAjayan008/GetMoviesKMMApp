import SwiftUI
import KMPObservableViewModelSwiftUI
import shared

struct HomeScreen: View {
    
    @StateViewModel 
    var viewModel = TestViewmodel()
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack{
            ScrollView{
                LazyVGrid(columns:gridColumns,spacing: 16){
                    ForEach(sampleMovie) { item in
                        NavigationLink(value: item){
                            MovieGridItem(movie: item)
                        }.buttonStyle(PlainButtonStyle())
                    }
                }
            }
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
