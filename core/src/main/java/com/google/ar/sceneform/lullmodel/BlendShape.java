// automatically generated by the FlatBuffers compiler, do not modify

package com.google.ar.sceneform.lullmodel;

import java.nio.*;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
/**
 * Matches the format of the original mesh, but potentially supplies new
 * normals and positions for each vertex. This is used by BlendSystem to
 * support blend shape animation (morph target animation) in Lullaby.
 * If a blend shape vertex is identical to the base mesh vertex OR only differs
 * from the original mesh vertex in its Tangent attribute, the BlendShape only
 * stores the original mesh index and the Tangent data to reduce the overall
 * asset size. The vertex will be re-generated at load time.
 */
public final class BlendShape extends Table {
  public static BlendShape getRootAsBlendShape(ByteBuffer _bb) { return getRootAsBlendShape(_bb, new BlendShape()); }
  public static BlendShape getRootAsBlendShape(ByteBuffer _bb, BlendShape obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public BlendShape __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * The name of this blend shape.
   */
  public long name() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  /**
   * Contents vertex data of mesh vertices but with positions and normals
   * adjusted to match this blend shape. Store only those vertices that differ
   * from mesh vertices in attributes beyond Tangent.
   */
  public int vertexData(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int vertexDataLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer vertexDataAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer vertexDataInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  /**
   * Indices of fully stored vertices. Will either be an array of 16-bit or
   * 32-bit values.
   */
  public long vertexIndices32(int j) { int o = __offset(8); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int vertexIndices32Length() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer vertexIndices32AsByteBuffer() { return __vector_as_bytebuffer(8, 4); }
  public ByteBuffer vertexIndices32InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 4); }
  public int vertexIndices16(int j) { int o = __offset(10); return o != 0 ? bb.getShort(__vector(o) + j * 2) & 0xFFFF : 0; }
  public int vertexIndices16Length() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer vertexIndices16AsByteBuffer() { return __vector_as_bytebuffer(10, 2); }
  public ByteBuffer vertexIndices16InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 10, 2); }
  /**
   * Contains Tangent data that is necessary to restore original values of
   * blend shape vertices that differ from correspondent mesh vertices in
   * Tangent attribute only OR indentical ones.
   */
  public int tangentData(int j) { int o = __offset(12); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int tangentDataLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer tangentDataAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public ByteBuffer tangentDataInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 12, 1); }
  /**
   * Indices of vertices that differ in Tangent OR indentical ones. Will either
   * be an array of 16-bit or 32-bit values.
   */
  public long tangentIndices32(int j) { int o = __offset(14); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int tangentIndices32Length() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer tangentIndices32AsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  public ByteBuffer tangentIndices32InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 4); }
  public int tangentIndices16(int j) { int o = __offset(16); return o != 0 ? bb.getShort(__vector(o) + j * 2) & 0xFFFF : 0; }
  public int tangentIndices16Length() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer tangentIndices16AsByteBuffer() { return __vector_as_bytebuffer(16, 2); }
  public ByteBuffer tangentIndices16InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 16, 2); }

  public static int createBlendShape(FlatBufferBuilder builder,
                                     long name,
                                     int vertex_dataOffset,
                                     int vertex_indices32Offset,
                                     int vertex_indices16Offset,
                                     int tangent_dataOffset,
                                     int tangent_indices32Offset,
                                     int tangent_indices16Offset) {
    builder.startObject(7);
    BlendShape.addTangentIndices16(builder, tangent_indices16Offset);
    BlendShape.addTangentIndices32(builder, tangent_indices32Offset);
    BlendShape.addTangentData(builder, tangent_dataOffset);
    BlendShape.addVertexIndices16(builder, vertex_indices16Offset);
    BlendShape.addVertexIndices32(builder, vertex_indices32Offset);
    BlendShape.addVertexData(builder, vertex_dataOffset);
    BlendShape.addName(builder, name);
    return BlendShape.endBlendShape(builder);
  }

  public static void startBlendShape(FlatBufferBuilder builder) { builder.startObject(7); }
  public static void addName(FlatBufferBuilder builder, long name) { builder.addInt(0, (int)name, (int)0L); }
  public static void addVertexData(FlatBufferBuilder builder, int vertexDataOffset) { builder.addOffset(1, vertexDataOffset, 0); }
  public static int createVertexDataVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createVertexDataVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startVertexDataVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addVertexIndices32(FlatBufferBuilder builder, int vertexIndices32Offset) { builder.addOffset(2, vertexIndices32Offset, 0); }
  public static int createVertexIndices32Vector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startVertexIndices32Vector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addVertexIndices16(FlatBufferBuilder builder, int vertexIndices16Offset) { builder.addOffset(3, vertexIndices16Offset, 0); }
  public static int createVertexIndices16Vector(FlatBufferBuilder builder, short[] data) { builder.startVector(2, data.length, 2); for (int i = data.length - 1; i >= 0; i--) builder.addShort(data[i]); return builder.endVector(); }
  public static void startVertexIndices16Vector(FlatBufferBuilder builder, int numElems) { builder.startVector(2, numElems, 2); }
  public static void addTangentData(FlatBufferBuilder builder, int tangentDataOffset) { builder.addOffset(4, tangentDataOffset, 0); }
  public static int createTangentDataVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createTangentDataVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startTangentDataVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addTangentIndices32(FlatBufferBuilder builder, int tangentIndices32Offset) { builder.addOffset(5, tangentIndices32Offset, 0); }
  public static int createTangentIndices32Vector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startTangentIndices32Vector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTangentIndices16(FlatBufferBuilder builder, int tangentIndices16Offset) { builder.addOffset(6, tangentIndices16Offset, 0); }
  public static int createTangentIndices16Vector(FlatBufferBuilder builder, short[] data) { builder.startVector(2, data.length, 2); for (int i = data.length - 1; i >= 0; i--) builder.addShort(data[i]); return builder.endVector(); }
  public static void startTangentIndices16Vector(FlatBufferBuilder builder, int numElems) { builder.startVector(2, numElems, 2); }
  public static int endBlendShape(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

