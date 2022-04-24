//
//  TextField.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/24/22.
//

import SwiftUI

struct RectangleTextField: View {
    var fieldHint: String
    @Binding var nameOfField: String
    
    var body: some View {
        TextField(fieldHint,text:$nameOfField)
            .autocapitalization(.none)
            .padding()
            .background(RoundedRectangle(cornerRadius:6).stroke(Color.blue, lineWidth:2))
            .padding(.top, 0)
    }
}

struct RectangleTextField_Previews: PreviewProvider {
    static var previews: some View {
        RectangleTextField(fieldHint: "Username or Email", nameOfField: .constant(""))
    }
}

struct PasswordField: View {
    var fieldHint: String
    @Binding var nameOfField: String
    @State private var passwordVisible = false
    
    var body: some View {
        HStack(spacing: 15){
            VStack{
                if self.passwordVisible {
                    TextField(fieldHint, text: self.$nameOfField)
                        .autocapitalization(.none)
                } else {
                    SecureField(fieldHint, text: self.$nameOfField)
                        .autocapitalization(.none)
                }
            }
            
            Button(action: {
                self.passwordVisible.toggle()
            }) {
                Image(systemName: self.passwordVisible ? "eye.slash.fill" : "eye.fill")
                    .foregroundColor(Color.black.opacity(0.7))
                    .opacity(0.6)
            }
        }
        .padding()
        .background(RoundedRectangle(cornerRadius: 6).stroke(Color.blue, lineWidth: 2))
        .padding(.top, 10)
    }
}

struct PasswordField_Previews: PreviewProvider {
    static var previews: some View {
        PasswordField(fieldHint: "Password", nameOfField: .constant(""))
    }
}
