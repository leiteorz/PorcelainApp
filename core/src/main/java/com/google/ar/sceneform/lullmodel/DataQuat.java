// automatically generated by the FlatBuffers compiler, do not modify

package com.google.ar.sceneform.lullmodel;

import java.nio.*;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
/**
 * Data type for quaternion values to be stored in a VariantDef.
 */
public final class DataQuat extends Table {
  public static DataQuat getRootAsDataQuat(ByteBuffer _bb) { return getRootAsDataQuat(_bb, new DataQuat()); }
  public static DataQuat getRootAsDataQuat(ByteBuffer _bb, DataQuat obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public DataQuat __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Quat value() { return value(new Quat()); }
  public Quat value(Quat obj) { int o = __offset(4); return o != 0 ? obj.__assign(o + bb_pos, bb) : null; }

  public static void startDataQuat(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addValue(FlatBufferBuilder builder, int valueOffset) { builder.addStruct(0, valueOffset, 0); }
  public static int endDataQuat(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
