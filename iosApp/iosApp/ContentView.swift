import SwiftUI
import shared

struct ContentView: View {
    @EnvironmentObject var appObj: AppObservableObject
    @State private var selectedTab : Int = 0
	let greet = Greeting().greeting()

	var body: some View {
        let screenState = appObj.nav.stateProvider.getToCast(screenIdentifier: ScreenIdentifier(screen: .list, params: nil)) as! CountriesListState
        NavigationView {
            if selectedTab == 0 {
                Text(greet + "\(screenState.countriesListItems)").navigationBarTitle("Home", displayMode: .inline)
            } else {
                Text("About").navigationBarTitle("About", displayMode: .inline)
            }
        }
        .toolbar {
            ToolbarItemGroup(placement: .bottomBar) {
                bottomBar(selectedTab: selectedTab, onTap: { selectedTap in
                    print("inx \(selectedTap)")
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
