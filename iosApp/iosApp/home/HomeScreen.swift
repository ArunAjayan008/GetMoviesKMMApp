import SwiftUI
import KMPObservableViewModelSwiftUI
import shared

struct HomeScreen: View {
    
    @StateViewModel var viewModel = HomeViewModel()
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack {
            ScrollView {
                LazyVGrid(columns: gridColumns, spacing: 16) {
                    MoviesGridView(movies: viewModel.uiState.movies, isLoading: viewModel.uiState.loading, loadFinished: viewModel.uiState.loadFinished, loadMovies: { await viewModel.loadMovies(forceReload: $0) })
                    
                    if viewModel.uiState.loading {
                        LoadingView()
                    }
                }
                .padding(.horizontal, 12)
                .navigationDestination(for: Movie.self) { movie in
                    DetailScreen(movie: movie)
                }
            }
            .navigationTitle("Movies")
        }
        .task {
            await viewModel.loadMovies(forceReload: true)
        }
    }
}

struct MoviesGridView: View {
    let movies: [Movie]
    let isLoading: Bool
    let loadFinished: Bool
    let loadMovies: (Bool) async -> Void
    
    var body: some View {
        ForEach(movies, id: \.id) { movie in
            NavigationLink(value: movie) {
                MovieGridItem(movie: movie)
                    .task {
                        if movie == movies.last && !isLoading && !loadFinished {
                            await loadMovies(false)
                        }
                    }
            }
            .buttonStyle(PlainButtonStyle())
        }
    }
}

struct LoadingView: View {
    var body: some View {
        Section(footer: ProgressView()) {}
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
