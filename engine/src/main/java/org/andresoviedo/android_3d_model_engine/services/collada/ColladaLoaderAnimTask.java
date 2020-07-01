package org.andresoviedo.android_3d_model_engine.services.collada;

import android.app.Activity;
import android.net.Uri;

import org.andresoviedo.android_3d_model_engine.model.Object3DData;
import org.andresoviedo.android_3d_model_engine.services.LoaderTask;
import org.andresoviedo.android_3d_model_engine.services.collada.entities.AnimatedModelData;
import org.andresoviedo.android_3d_model_engine.services.collada.loader.ColladaLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ColladaLoaderAnimTask extends LoaderTask {

    AnimatedModelData modelData;
    private Uri animUri;

    public ColladaLoaderAnimTask(Activity parent, Uri uri, Uri animUri, Callback callback) {
        super(parent, uri, callback);
        this.animUri = animUri;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<Object3DData> build() throws IOException {
        // Parse DAE
        Object[] ret = ColladaLoader.buildAnimatedModel(new URL(uri.toString()));

//        if (animUri != null)  {
//            Object[] animRet = ColladaLoader.buildAnimatedModel(new URL(animUri.toString()));
//            modelData = (AnimatedModelData) animRet[0];
//        } else {
//            modelData = (AnimatedModelData) ret[0];
//        }
        List<Object3DData> datas = (List<Object3DData>) ret[1];
        modelData = (AnimatedModelData) ret[0];
        return datas;
    }

    @Override
    protected void build(List<Object3DData> datas) throws Exception {
        ColladaLoader.populateAnimatedModel(new URL(animUri.toString()), datas, modelData);
        if (datas.size() == 1) {
            datas.get(0).centerAndScale(5, new float[]{0, 0, 0});
        } else {
            Object3DData.centerAndScale(datas, 5, new float[]{0, 0, 0});
        }
    }

}
