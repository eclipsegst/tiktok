//
//  BottomBar.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/18/22.
//

import SwiftUI

@ViewBuilder func bottomBar(selectedTab: Int = 0, onTap: @escaping ((Int) -> Void)) -> some View {
    Spacer()
    BottomBarButton(
        itemLabel: "Home",
        iconName: "list.bullet",
        selected: selectedTab == 0,
        onClick: { onTap(0) }
    )
    Spacer()
    BottomBarButton(
        itemLabel: "About",
        iconName: "list.bullet",
        selected: selectedTab == 1,
        onClick: {
            onTap(1)
        }
    )
    Spacer()
}

struct BottomBarButton: View {
    var itemLabel : String
    var iconName : String
    var selected : Bool
    var onClick : () -> Void
    
    var body: some View {
        Button(action: { onClick() }) {
            VStack(spacing: 5) {
                Image(systemName: iconName).resizable().scaledToFit().frame(height:15)
                Text(itemLabel).font(Font.footnote)
            }
            .foregroundColor(selected ? .orange : .gray)
        }
    }
}
