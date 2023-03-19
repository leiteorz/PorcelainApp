// automatically generated by the FlatBuffers compiler, do not modify

package com.google.ar.sceneform.lullmodel;

import java.nio.*;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
/**
 * Information about how to import an asset into the model_pipeline.
 */
public final class ModelPipelineImportDef extends Table {
  public static ModelPipelineImportDef getRootAsModelPipelineImportDef(ByteBuffer _bb) { return getRootAsModelPipelineImportDef(_bb, new ModelPipelineImportDef()); }
  public static ModelPipelineImportDef getRootAsModelPipelineImportDef(ByteBuffer _bb, ModelPipelineImportDef obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public ModelPipelineImportDef __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * The short name of the asset that is referenced by the individual model
   * components (eg. renderables, collidables, etc.) below.
   */
  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer nameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  /**
   * The location of disk of the asset.
   */
  public String file() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer fileAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer fileInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  /**
   * Inserts an extra node into the asset hierarchy so that the resulting model
   * is centered around the origin.
   */
  public boolean recenter() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Multiplier applied to the model to change its scale.
   */
  public float scale() { int o = __offset(10); return o != 0 ? bb.getFloat(o + bb_pos) : 1.0f; }
  /**
   * The axis system used by the model asset.
   */
  public int axisSystem() { int o = __offset(12); return o != 0 ? bb.getInt(o + bb_pos) : -1; }
  /**
   * The limit angle (in degrees) between two normals being considered for
   * tangent space smoothing.
   */
  public float smoothingAngle() { int o = __offset(14); return o != 0 ? bb.getFloat(o + bb_pos) : 45.0f; }
  /**
   * Limit per-vertex bone weights to the N most significant bones.
   */
  public int maxBoneWeights() { int o = __offset(16); return o != 0 ? bb.getInt(o + bb_pos) : 4; }
  /**
   * some clients do not use LOG to report errors
   */
  public boolean reportErrorsToStdout() { int o = __offset(18); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Inverts vertical texture coordinates when enabled (D3D/OGL difference).
   */
  public boolean flipTextureCoordinates() { int o = __offset(20); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Pre-transforms vertices by their node hierarchy so that all vertices are
   * in the same object-space, and the node hierarchy is flattened.
   */
  public boolean flattenHierarchyAndTransformVerticesToRootSpace() { int o = __offset(22); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * If a model contains both metallic-roughness textures and
   * specular-glossiness textures, this flag causes the import to only use the
   * specular-glossiness textures. Otherwise, it uses the metallic-rougness
   * textures.
   */
  public boolean useSpecularGlossinessTexturesIfPresent() { int o = __offset(24); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Toggle for Assimp's aiProcess_FixInfacingNormals process.
   */
  public boolean fixInfacingNormals() { int o = __offset(26); return o != 0 ? 0!=bb.get(o + bb_pos) : true; }
  /**
   * Encodes a sign into the w value of the orientation quaternion such that >0
   * implies a right handed space, and <0 implies a left handed space. w==0
   * should never happen. This allows orientation components to be encoded in a
   */
  public boolean ensureVertexOrientationWNotZero() { int o = __offset(28); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Distinct from 'scale'; defines the unit we expect positions to be in.
   * Kept as 0 for backwards compatibility, this would be 100.0 for contexts
   * where world units would be measured in meters, and 2.54 for inches.
   */
  public float cmPerUnit() { int o = __offset(30); return o != 0 ? bb.getFloat(o + bb_pos) : 0.0f; }
  /**
   * The names of the nodes that contain the target meshes to import from the
   * asset. If a node has a mesh and its name is present in this list, the mesh
   * data will be added to the output model. If this list is empty, all meshes
   * will be added to the output model.
   */
  public String targetMeshes(int j) { int o = __offset(32); return o != 0 ? __string(__vector(o) + j * 4) : null; }
  public int targetMeshesLength() { int o = __offset(32); return o != 0 ? __vector_len(o) : 0; }
  public boolean mergeMaterials() { int o = __offset(34); return o != 0 ? 0!=bb.get(o + bb_pos) : true; }

  public static int createModelPipelineImportDef(FlatBufferBuilder builder,
                                                 int nameOffset,
                                                 int fileOffset,
                                                 boolean recenter,
                                                 float scale,
                                                 int axis_system,
                                                 float smoothing_angle,
                                                 int max_bone_weights,
                                                 boolean report_errors_to_stdout,
                                                 boolean flip_texture_coordinates,
                                                 boolean flatten_hierarchy_and_transform_vertices_to_root_space,
                                                 boolean use_specular_glossiness_textures_if_present,
                                                 boolean fix_infacing_normals,
                                                 boolean ensure_vertex_orientation_w_not_zero,
                                                 float cm_per_unit,
                                                 int target_meshesOffset,
                                                 boolean merge_materials) {
    builder.startObject(16);
    ModelPipelineImportDef.addTargetMeshes(builder, target_meshesOffset);
    ModelPipelineImportDef.addCmPerUnit(builder, cm_per_unit);
    ModelPipelineImportDef.addMaxBoneWeights(builder, max_bone_weights);
    ModelPipelineImportDef.addSmoothingAngle(builder, smoothing_angle);
    ModelPipelineImportDef.addAxisSystem(builder, axis_system);
    ModelPipelineImportDef.addScale(builder, scale);
    ModelPipelineImportDef.addFile(builder, fileOffset);
    ModelPipelineImportDef.addName(builder, nameOffset);
    ModelPipelineImportDef.addMergeMaterials(builder, merge_materials);
    ModelPipelineImportDef.addEnsureVertexOrientationWNotZero(builder, ensure_vertex_orientation_w_not_zero);
    ModelPipelineImportDef.addFixInfacingNormals(builder, fix_infacing_normals);
    ModelPipelineImportDef.addUseSpecularGlossinessTexturesIfPresent(builder, use_specular_glossiness_textures_if_present);
    ModelPipelineImportDef.addFlattenHierarchyAndTransformVerticesToRootSpace(builder, flatten_hierarchy_and_transform_vertices_to_root_space);
    ModelPipelineImportDef.addFlipTextureCoordinates(builder, flip_texture_coordinates);
    ModelPipelineImportDef.addReportErrorsToStdout(builder, report_errors_to_stdout);
    ModelPipelineImportDef.addRecenter(builder, recenter);
    return ModelPipelineImportDef.endModelPipelineImportDef(builder);
  }

  public static void startModelPipelineImportDef(FlatBufferBuilder builder) { builder.startObject(16); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addFile(FlatBufferBuilder builder, int fileOffset) { builder.addOffset(1, fileOffset, 0); }
  public static void addRecenter(FlatBufferBuilder builder, boolean recenter) { builder.addBoolean(2, recenter, false); }
  public static void addScale(FlatBufferBuilder builder, float scale) { builder.addFloat(3, scale, 1.0f); }
  public static void addAxisSystem(FlatBufferBuilder builder, int axisSystem) { builder.addInt(4, axisSystem, -1); }
  public static void addSmoothingAngle(FlatBufferBuilder builder, float smoothingAngle) { builder.addFloat(5, smoothingAngle, 45.0f); }
  public static void addMaxBoneWeights(FlatBufferBuilder builder, int maxBoneWeights) { builder.addInt(6, maxBoneWeights, 4); }
  public static void addReportErrorsToStdout(FlatBufferBuilder builder, boolean reportErrorsToStdout) { builder.addBoolean(7, reportErrorsToStdout, false); }
  public static void addFlipTextureCoordinates(FlatBufferBuilder builder, boolean flipTextureCoordinates) { builder.addBoolean(8, flipTextureCoordinates, false); }
  public static void addFlattenHierarchyAndTransformVerticesToRootSpace(FlatBufferBuilder builder, boolean flattenHierarchyAndTransformVerticesToRootSpace) { builder.addBoolean(9, flattenHierarchyAndTransformVerticesToRootSpace, false); }
  public static void addUseSpecularGlossinessTexturesIfPresent(FlatBufferBuilder builder, boolean useSpecularGlossinessTexturesIfPresent) { builder.addBoolean(10, useSpecularGlossinessTexturesIfPresent, false); }
  public static void addFixInfacingNormals(FlatBufferBuilder builder, boolean fixInfacingNormals) { builder.addBoolean(11, fixInfacingNormals, true); }
  public static void addEnsureVertexOrientationWNotZero(FlatBufferBuilder builder, boolean ensureVertexOrientationWNotZero) { builder.addBoolean(12, ensureVertexOrientationWNotZero, false); }
  public static void addCmPerUnit(FlatBufferBuilder builder, float cmPerUnit) { builder.addFloat(13, cmPerUnit, 0.0f); }
  public static void addTargetMeshes(FlatBufferBuilder builder, int targetMeshesOffset) { builder.addOffset(14, targetMeshesOffset, 0); }
  public static int createTargetMeshesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startTargetMeshesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMergeMaterials(FlatBufferBuilder builder, boolean mergeMaterials) { builder.addBoolean(15, mergeMaterials, true); }
  public static int endModelPipelineImportDef(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

