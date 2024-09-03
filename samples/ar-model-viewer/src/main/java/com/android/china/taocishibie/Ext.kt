package com.android.china.taocishibie

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.android.china.model.ResultEasydl
import com.google.ar.sceneform.samples.gltf.ml.China2Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.util.Arrays

var allTypeOfChina = arrayOf(
    "唐长沙窑青釉褐斑模印贴花椰枣纹执壶",
    "唐长沙窑青釉褐绿彩双雁云气纹油壶",
    "渌水悠悠瓶",
    "白陶斧",
    "蓝白釉开创红楼梦喜剧人物故事瓷瓶",
    "蓝釉镂空浮雕热爱和平天安门红场图案带座天球瓶",
    "象形铜尊",
    "釉上彩蝴蝶纹撇口瓶",
    "釉上粉彩昭君出塞山水兽耳瓶",
    "釉下五彩人物故事瓶",
    "釉下五彩各族人民大团结瓷瓶",
    "釉下五彩孔雀牡丹纹瓷花瓶",
    "釉下五彩山水楼阁图瓶",
    "釉下五彩扁豆双禽纹凤尾尊",
    "釉下五彩松鹤纹瓷瓶",
    "釉下五彩牡丹耄耋纹琵琶尊",
    "釉下五彩玉兰牡丹纹带座瓷瓶",
    "釉下五彩竹林七贤纹瓷赏瓶",
    "釉下五彩花卉孔雀纹瓷瓶",
    "釉下五彩花鸟纹瓷象鼻花瓶",
    "釉下五彩锦鸡牡丹纹凤尾尊",
    "釉下五彩青花缠枝瓷瓶",
    "釉下五彩黑底满花瓶",
    "釉下五彩龙纹瓷瓶",
    "釉下墨彩瓷琵琶尊",
    "釉下复合彩花卉瓶",
    "釉下孔雀牡丹青花瓷瓶",
    "釉下彩松鹤纹瓷瓶",
    "釉下祭蓝瓶"
)

fun getResult(
    context: Context,
    bitmap: Bitmap
) : ResultEasydl{

    /**
     * @param model 模型创建
     * @param inputFeature 参考输入
     */
    val model = China2Model.newInstance(context)

    val inputFeature = TensorBuffer.createFixedSize(intArrayOf(1,224,224,3),DataType.FLOAT32)

    /**
     * @param resizeBitmap 输入的Bitmap改变
     * @param tensorImage 输入的图片
     * 将要推理的图片规范化
     */
    val resizeBitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)
    var tensorImage = TensorImage(DataType.FLOAT32)
    tensorImage.load(resizeBitmap)
    val byteBuffer = tensorImage.buffer
    inputFeature.loadBuffer(byteBuffer)

    /**
     *@param outputFeature 推理结果
     * 运行模型并获取推理结果
     */
    val outputs = model.process(inputFeature)
    val outputFeature = outputs.outputFeature0AsTensorBuffer

    /**
     * 不浪费资源，关闭模型。
     */
    model.close()

    /**
     * 获取概率值与相对应的瓷器种类。
     */
    var maxIndex = 0
    val array = outputFeature.floatArray
    Arrays.sort(array)
    val maxValue = array[array.size - 1]
    for (i in outputFeature.floatArray) {
        if (i == maxValue) {
            break
        } else {
            maxIndex++
        }
    }
    return ResultEasydl(allTypeOfChina[maxIndex], maxValue.toDouble());
}
