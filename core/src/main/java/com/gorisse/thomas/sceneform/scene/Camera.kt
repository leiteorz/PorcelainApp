package com.gorisse.thomas.sceneform.scene

import com.google.android.filament.Camera
import com.google.android.filament.utils.pow
import com.gorisse.thomas.sceneform.Filament
import kotlin.math.log2

/**
 * ### Computes the camera's EV100 from exposure settings
 *
 * Exposure value (EV) is a number that represents a combination of a camera's shutter speed and
 * f-number, such that all combinations that yield the same exposure have the same EV
 * (for any fixed scene luminance)
 *
 * Reference: https://en.wikipedia.org/wiki/Exposure_value
 */
val Camera.ev100: Float
    get() = log2((aperture * aperture) / shutterSpeed * 100.0f / sensitivity)

/**
 * ### Computes the exposure normalization factor from the camera's EV100
 *
 * Calculate a unit-less intensity scale from the actual Filament camera settings
 *
 * This method is useful when trying to match the lighting of other engines or tools.
 * Many engines/tools use unit-less light intensities, which can be matched by setting
 * the exposure manually.
 *
 * Filament uses a physically based camera model, with aperture, shutter speed, and sensitivity
 * (ISO).
 * This plays nicely with physical light units (for instance if you measure the sun outside on a
 * sunny day in California you'll get ~110,000 lux and if you set your camera settings to
 * ƒ/16 1/125s ISO 100, exposure will be correct). However many engines use "relative exposure"
 * instead, where exposure is just a float and set to 1.0 by default.
 *
 * ARCore's light estimation was designed to work with those engines.
 * So we scale light intensities estimations by multiplying the values provided by ARCore
 * by 1.0 / log2((aperture * aperture) / shutterSpeed * 100.0f / sensitivity).
 * This should raise ARCore's values to something that's physically plausible for the chosen
 * exposure.
 *
 * **Sources:**
 *  - [https://github.com/SceneView/sceneform-android/pull/156#issuecomment-911873565]
 *  - [https://github.com/google/filament/blob/main/filament/src/Exposure.cpp#L46]
 */
val Camera.exposureFactor get() = 1.0f / ev100

val Camera.illuminance get() = illuminance(ev100)
fun Camera.illuminance(ev100: Float) = 2.5f * pow(2.0f, ev100)

val Camera.luminance get() = luminance(ev100)
fun Camera.luminance(ev100: Float) = pow(2.0f, ev100 - 3.0f)

/**
 * Destroys the Camera component associated with the camera entity.
 */
fun Camera.destroy() {
    Filament.engine.destroyCameraComponent(entity)
    Filament.entityManager.destroy(entity)
}