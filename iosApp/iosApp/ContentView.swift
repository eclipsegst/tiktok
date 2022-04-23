import SwiftUI
import shared

struct ContentView: View {
    @EnvironmentObject var appObj: AppObservableObject
    @State private var selectedTab : ScreenState = .home

	var body: some View {
        NavigationView {
            switch selectedTab {
            case .home:
                HomeScreen()
            case .me:
                MeScreen()
            }
        }
        .toolbar {
            ToolbarItemGroup(placement: .bottomBar) {
                bottomBar(selectedTab: selectedTab, onTap: { selectedTap in
                    selectedTab = selectedTap
                })
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

public enum ScreenState: Equatable {
    case home
    case me
}
