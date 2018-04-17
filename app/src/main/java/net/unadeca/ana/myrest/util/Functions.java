package net.unadeca.ana.myrest.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.unadeca.ana.myrest.R;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by ANA on 13/04/2018.
 */

public class Functions {
    private static RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher)
            .override(400, 500)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    public static void loadImage(String image, ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(image)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);
    }
    // esto es nuevo borrar
    public static void loadImage(File image, ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(image)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);
    }
    //asta aqui

    public static void loadImage(ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(R.mipmap.ic_launcher_round)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);
    }
//esto hachea y copila la contraseña en tiempo real
    public static String md5(String s) {
        return digest(s, "MD5");
    }

    private static char[] hextable = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String byteArrayToHex(byte[] array) {
        String s = "";
        for (int i = 0; i < array.length; ++i) {
            int di = (array[i] + 256) & 0xFF; // Make it unsigned
            s = s + hextable[(di >> 4) & 0xF] + hextable[di & 0xF];
        }
        return s;
    }

    public static String digest(String s, String algorithm) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return s;
        }
        m.update(s.getBytes(), 0, s.length());
        return byteArrayToHex(m.digest());
    }
}



