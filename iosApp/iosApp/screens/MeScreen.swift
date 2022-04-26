//
//  MeScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

struct MeScreen: View {
    
    var body: some View {
        VStack {
            Image("plant")
                .resizable()
                .frame(width: 60.0, height: 60.0)
                .clipShape(Circle())
                .shadow(radius: 10)
                .overlay(Circle().stroke(Color.orange, lineWidth: 1))
            
            Text("@username")
                .font(.system(size: 14).bold())
                .foregroundColor(.black.opacity(0.8))
                .padding()
            
            HStack {
                Spacer()
                FollowBlock(count: 1, title: "Following")
                FollowBlock(count: 2, title: "Followers")
                FollowBlock(count: 3, title: "Likes")
                Spacer()
            }
            
            HStack {
                Button(action: {
                    print("Edit profile tapped!")
                }) {
                    Text("Edit profile")
                        .fontWeight(.semibold)
                        .font(.system(size: 14).bold())
                        .foregroundColor(.black.opacity(0.8))
                        .padding(.horizontal, 30)
                        .padding(.vertical, 10)
                        .background(RoundedRectangle(cornerRadius: 2).stroke(.gray.opacity(0.4), lineWidth: 0.5))
                }
            }
            
            NavigationLink(destination: SettingsScreen()) {
                HStack {
                    Image(systemName: "gearshape").foregroundColor(.blue)
                    Text("Settings").foregroundColor(.black)
                    Spacer()
                    Image(systemName: "chevron.right").foregroundColor(.gray)
                }.padding()
            }
            
            Spacer()
                
        }.navigationBarTitle("", displayMode: .inline)
    }
}

struct FollowBlock: View {
    var count = 0
    var title = ""
    var body: some View {
        VStack(alignment: .center) {
            Text("\(count)")
                .font(.system(size: 14).bold())
                .foregroundColor(.black.opacity(0.8))
            Text(title)
                .font(.system(size: 12))
                .fontWeight(.light)
                .foregroundColor(.gray)
        }.frame(minWidth: 80)
    }
}
