package glm_.vec4

import glm_.*
import glm_.vec2.Vec2bool
import glm_.vec2.Vec2t
import glm_.vec3.Vec3
import glm_.vec3.Vec3bool
import glm_.vec3.Vec3t
import glm_.vec4.operators.vec4_operators
import java.io.InputStream
import java.nio.*

/**
 * Created by elect on 09/10/16.
 */

class Vec4(x: Float, y: Float, z: Float, w: Float) : Vec4t<Float>(x, y, z, w) {

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor() : this(0)

    constructor(v: Vec2t<out Number>) : this(v.x, v.y, 0, 1)
    constructor(a: Vec2t<out Number>, b: Vec2t<out Number>) : this(a.x, a.y, b.x, b.y)  // TODO others
    constructor(v: Vec2t<out Number>, z: Number, w: Number) : this(v.x, v.y, z, w)
    constructor(v: Vec3t<out Number>) : this(v, 1)
    constructor(v: Vec3t<out Number>, w: Number) : this(v.x, v.y, v.z, w)
    constructor(x: Number, v: Vec3t<out Number>) : this(x, v.x, v.y, v.z)
    constructor(v: Vec4t<out Number>) : this(v.x, v.y, v.z, v.w)

    constructor(v: Vec2bool) : this(v.x.f, v.y.f, 0, 1) // TODO scalar to main.getF
    constructor(v: Vec3bool) : this(v.x.f, v.y.f, v.z.f, 1)
    constructor(v: Vec4bool) : this(v.x.f, v.y.f, v.z.f, v.w.f)

    // TODO oneByteOne* = false
    constructor(bytes: ByteArray, index: Int = 0, oneByteOneFloat: Boolean = false, bigEndian: Boolean = true) : this(
            if (oneByteOneFloat) bytes[index].f else bytes.getFloat(index, bigEndian),
            if (oneByteOneFloat) bytes[index + 1].f else bytes.getFloat(index + Float.BYTES, bigEndian),
            if (oneByteOneFloat) bytes[index + 2].f else bytes.getFloat(index + Float.BYTES * 2, bigEndian),
            if (oneByteOneFloat) bytes[index + 3].f else bytes.getFloat(index + Float.BYTES * 3, bigEndian))

    constructor(chars: CharArray, index: Int = 0) : this(chars[index].f, chars[index + 1].f, chars[index + 2].f, chars[index + 3].f)
    constructor(shorts: ShortArray, index: Int = 0) : this(shorts[index], shorts[index + 1], shorts[index + 2], shorts[index + 3])
    constructor(ints: IntArray, index: Int = 0) : this(ints[index], ints[index + 1], ints[index + 2], ints[index + 3])
    constructor(longs: LongArray, index: Int = 0) : this(longs[index], longs[index + 1], longs[index + 2], longs[index + 3])
    constructor(floats: FloatArray, index: Int = 0) : this(floats[index], floats[index + 1], floats[index + 2], floats[index + 3])
    constructor(doubles: DoubleArray, index: Int = 0) : this(doubles[index], doubles[index + 1], doubles[index + 2], doubles[index + 3])
    constructor(booleans: BooleanArray, index: Int = 0) : this(booleans[index].f, booleans[index + 1].f, booleans[index + 2].f, booleans[index + 3].f)

    constructor(numbers: Array<out Number>, index: Int = 0) : this(numbers[index], numbers[index + 1], numbers[index + 2], numbers[index + 3])
    constructor(chars: Array<Char>, index: Int = 0) : this(chars[index].f, chars[index + 1].f, chars[index + 2].f, chars[index + 3].f)
    constructor(booleans: Array<Boolean>, index: Int = 0) : this(booleans[index].f, booleans[index + 1].f, booleans[index + 2].f, booleans[index + 3].f)

    constructor(list: List<Any>, index: Int = 0) : this(list[index].toFloat, list[index + 1].toFloat, list[index + 2].toFloat, list[index + 3].toFloat)

    constructor(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneFloat: Boolean = false) : this(
            if (oneByteOneFloat) bytes[index].f else bytes.getFloat(index),
            if (oneByteOneFloat) bytes[index + 1].f else bytes.getFloat(index + Float.BYTES),
            if (oneByteOneFloat) bytes[index + 2].f else bytes.getFloat(index + Float.BYTES * 2),
            if (oneByteOneFloat) bytes[index + 3].f else bytes.getFloat(index + Float.BYTES * 3))

    constructor(chars: CharBuffer, index: Int = chars.position()) : this(chars[index].f, chars[index + 1].f, chars[index + 2].f, chars[index + 3].f)
    constructor(shorts: ShortBuffer, index: Int = shorts.position()) : this(shorts[index], shorts[index + 1], shorts[index + 2], shorts[index + 3])
    constructor(ints: IntBuffer, index: Int = ints.position()) : this(ints[index], ints[index + 1], ints[index + 2], ints[index + 3])
    constructor(longs: LongBuffer, index: Int = longs.position()) : this(longs[index], longs[index + 1], longs[index + 2], longs[index + 3])
    constructor(floats: FloatBuffer, index: Int = floats.position()) : this(floats[index], floats[index + 1], floats[index + 2], floats[index + 3])
    constructor(doubles: DoubleBuffer, index: Int = doubles.position()) : this(doubles[index], doubles[index + 1], doubles[index + 2], doubles[index + 3])

    constructor(block: (Int) -> Float) : this(block(0), block(1), block(2), block(3))

    constructor(s: Number) : this(s, s, s, s)
    constructor(x: Number, y: Number, z: Number, w: Number) : this(x.f, y.f, z.f, w.f)

    constructor(inputStream: InputStream, bigEndian: Boolean = true) :
            this(inputStream.float(bigEndian), inputStream.float(bigEndian), inputStream.float(bigEndian), inputStream.float(bigEndian))


    fun set(bytes: ByteArray, index: Int = 0, oneByteOneFloat: Boolean = false, bigEndian: Boolean = true) {
        x = if (oneByteOneFloat) bytes[index].f else bytes.getFloat(index, bigEndian)
        y = if (oneByteOneFloat) bytes[index + 1].f else bytes.getFloat(index + Float.BYTES, bigEndian)
        z = if (oneByteOneFloat) bytes[index + 2].f else bytes.getFloat(index + Float.BYTES * 2, bigEndian)
        w = if (oneByteOneFloat) bytes[index + 3].f else bytes.getFloat(index + Float.BYTES * 3, bigEndian)
    }

    fun set(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneFloat: Boolean = false) {
        x = if (oneByteOneFloat) bytes[index].f else bytes.getFloat(index)
        y = if (oneByteOneFloat) bytes[index + 1].f else bytes.getFloat(index + Float.BYTES)
        z = if (oneByteOneFloat) bytes[index + 2].f else bytes.getFloat(index + Float.BYTES * 2)
        w = if (oneByteOneFloat) bytes[index + 3].f else bytes.getFloat(index + Float.BYTES * 3)
    }


    override fun put(x: Number, y: Number, z: Number, w: Number): Vec4 {
        this.x = x.f
        this.y = y.f
        this.z = z.f
        this.w = w.f
        return this
    }


    infix fun to(floats: FloatArray) = to(floats, 0)
    fun to(floats: FloatArray, index: Int): FloatArray {
        floats[index] = x
        floats[index + 1] = y
        floats[index + 2] = z
        floats[index + 3] = w
        return floats
    }

    infix fun to(floats: FloatBuffer) = to(floats, floats.position())
    fun to(floats: FloatBuffer, index: Int): FloatBuffer {
        floats[index] = x
        floats[index + 1] = y
        floats[index + 2] = z
        floats[index + 3] = w
        return floats
    }

    override infix fun to(bytes: ByteBuffer) = to(bytes, bytes.position())
    override fun to(bytes: ByteBuffer, index: Int): ByteBuffer {
        bytes.putFloat(index, x)
        bytes.putFloat(index + Float.BYTES, y)
        bytes.putFloat(index + Float.BYTES * 2, z)
        bytes.putFloat(index + Float.BYTES * 3, w)
        return bytes
    }


    // -- Component accesses --

    override operator fun get(i: Int) = when (i) {
        0 -> x
        1 -> y
        2 -> z
        3 -> w
        else -> throw ArrayIndexOutOfBoundsException()
    }

    operator fun set(i: Int, s: Number) = when (i) {
        0 -> x = s.f
        1 -> y = s.f
        2 -> z = s.f
        3 -> w = s.f
        else -> throw ArrayIndexOutOfBoundsException()
    }


    companion object : vec4_operators() {
        @JvmField
        val length = 4
        @JvmField
        val size = length * Float.BYTES

        fun fromColor(r: Number, g: Number, b: Number, a: Number = 255f): Vec4 { // TODO constructor(Color)
            val sc = 1f / 255f
            return Vec4(r.f * sc, g.f * sc, b.f * sc, a.f * sc)
        }
    }

    override fun instanceSize() = size


    fun toVec3() = Vec3(this)
    fun to(res: Vec3) = res put this

    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Vec4(-x, -y, -z, -w)

    // -- Increment main.and decrement operators --

    operator fun inc(res: Vec4 = Vec4()) = plus(res, this, 1f, 1f, 1f, 1f)
    fun incAssign() = plus(this, this, 1f, 1f, 1f, 1f)


    operator fun dec(res: Vec4 = Vec4()) = minus(res, this, 1f, 1f, 1f, 1f)
    fun decAssign() = minus(this, this, 1f, 1f, 1f, 1f)


    // -- Specific binary arithmetic operators --

    operator fun plus(b: Float) = plus(Vec4(), this, b, b, b, b)
    operator fun plus(b: Vec4) = plus(Vec4(), this, b.x, b.y, b.z, b.w)

    fun plus(bX: Float, bY: Float, bZ: Float, bW: Float, res: Vec4 = Vec4()) = plus(res, this, bX, bY, bZ, bW)
    fun plus(b: Float, res: Vec4 = Vec4()) = plus(res, this, b, b, b, b)
    fun plus(b: Vec4, res: Vec4 = Vec4()) = plus(res, this, b.x, b.y, b.z, b.w)

    fun plusAssign(bX: Float, bY: Float, bZ: Float, bW: Float) = plus(this, this, bX, bY, bZ, bW)
    infix operator fun plusAssign(b: Float) {
        plus(this, this, b, b, b, b)
    }
    infix operator fun plusAssign(b: Vec4) {
        plus(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun minus(b: Float) = minus(Vec4(), this, b, b, b, b)
    operator fun minus(b: Vec4) = minus(Vec4(), this, b.x, b.y, b.z, b.w)

    fun minus(bX: Float, bY: Float, bZ: Float, bW: Float, res: Vec4 = Vec4()) = minus(res, this, bX, bY, bZ, bW)
    fun minus(b: Float, res: Vec4 = Vec4()) = minus(res, this, b, b, b, b)
    fun minus(b: Vec4, res: Vec4 = Vec4()) = minus(res, this, b.x, b.y, b.z, b.w)

    fun minusAssign(bX: Float, bY: Float, bZ: Float, bW: Float) = minus(this, this, bX, bY, bZ, bW)
    infix operator fun minusAssign(b: Float) {
        minus(this, this, b, b, b, b)
    }
    infix operator fun minusAssign(b: Vec4) {
        minus(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun times(b: Float) = times(Vec4(), this, b, b, b, b)
    operator fun times(b: Vec4) = times(Vec4(), this, b.x, b.y, b.z, b.w)

    fun times(bX: Float, bY: Float, bZ: Float, bW: Float, res: Vec4 = Vec4()) = times(res, this, bX, bY, bZ, bW)
    fun times(b: Float, res: Vec4 = Vec4()) = times(res, this, b, b, b, b)
    fun times(b: Vec4, res: Vec4 = Vec4()) = times(res, this, b.x, b.y, b.z, b.w)

    fun timesAssign(bX: Float, bY: Float, bZ: Float, bW: Float) = times(this, this, bX, bY, bZ, bW)
    infix operator fun timesAssign(b: Float) {
        times(this, this, b, b, b, b)
    }
    infix operator fun timesAssign(b: Vec4) {
        times(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun div(b: Float) = div(Vec4(), this, b, b, b, b)
    operator fun div(b: Vec4) = div(Vec4(), this, b.x, b.y, b.z, b.w)

    fun div(bX: Float, bY: Float, bZ: Float, bW: Float, res: Vec4 = Vec4()) = div(res, this, bX, bY, bZ, bW)
    fun div(b: Float, res: Vec4 = Vec4()) = div(res, this, b, b, b, b)
    fun div(b: Vec4, res: Vec4 = Vec4()) = div(res, this, b.x, b.y, b.z, b.w)

    fun divAssign(bX: Float, bY: Float, bZ: Float, bW: Float) = div(this, this, bX, bY, bZ, bW)
    infix operator fun divAssign(b: Float) {
        div(this, this, b, b, b, b)
    }
    infix operator fun divAssign(b: Vec4) {
        div(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun rem(b: Float) = rem(Vec4(), this, b, b, b, b)
    operator fun rem(b: Vec4) = rem(Vec4(), this, b.x, b.y, b.z, b.w)

    fun rem(bX: Float, bY: Float, bZ: Float, bW: Float, res: Vec4 = Vec4()) = rem(res, this, bX, bY, bZ, bW)
    fun rem(b: Float, res: Vec4 = Vec4()) = rem(res, this, b, b, b, b)
    fun rem(b: Vec4, res: Vec4 = Vec4()) = rem(res, this, b.x, b.y, b.z, b.w)

    fun remAssign(bX: Float, bY: Float, bZ: Float, bW: Float) = rem(this, this, bX, bY, bZ, bW)
    infix operator fun remAssign(b: Float) {
        rem(this, this, b, b, b, b)
    }
    infix operator fun remAssign(b: Vec4) {
        rem(this, this, b.x, b.y, b.z, b.w)
    }


    // -- Generic binary arithmetic operators --

    operator fun plus(b: Number) = plus(Vec4(), this, b.f, b.f, b.f, b.f)
    operator fun plus(b: Vec4t<out Number>) = plus(Vec4(), this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun plus(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4 = Vec4()) = plus(res, this, bX.f, bY.f, bZ.f, bW.f)
    fun plus(b: Number, res: Vec4 = Vec4()) = plus(res, this, b.f, b.f, b.f, b.f)
    fun plus(b: Vec4t<out Number>, res: Vec4 = Vec4()) = plus(res, this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun plusAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = plus(this, this, bX.f, bY.f, bZ.f, bW.f)
    infix operator fun plusAssign(b: Number) {
        plus(this, this, b.f, b.f, b.f, b.f)
    }
    infix operator fun plusAssign(b: Vec4t<out Number>) {
        plus(this, this, b.x.f, b.y.f, b.z.f, b.w.f)
    }


    operator fun minus(b: Number) = minus(Vec4(), this, b.f, b.f, b.f, b.f)
    operator fun minus(b: Vec4t<out Number>) = minus(Vec4(), this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun minus(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4 = Vec4()) = minus(res, this, bX.f, bY.f, bZ.f, bW.f)
    fun minus(b: Number, res: Vec4 = Vec4()) = minus(res, this, b.f, b.f, b.f, b.f)
    fun minus(b: Vec4t<out Number>, res: Vec4 = Vec4()) = minus(res, this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun minusAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = minus(this, this, bX.f, bY.f, bZ.f, bW.f)
    infix operator fun minusAssign(b: Number) {
        minus(this, this, b.f, b.f, b.f, b.f)
    }
    infix operator fun minusAssign(b: Vec4t<out Number>) {
        minus(this, this, b.x.f, b.y.f, b.z.f, b.w.f)
    }


    operator fun times(b: Number) = times(Vec4(), this, b.f, b.f, b.f, b.f)
    operator fun times(b: Vec4t<out Number>) = times(Vec4(), this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun times(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4 = Vec4()) = times(res, this, bX.f, bY.f, bZ.f, bW.f)
    fun times(b: Number, res: Vec4 = Vec4()) = times(res, this, b.f, b.f, b.f, b.f)
    fun times(b: Vec4t<out Number>, res: Vec4 = Vec4()) = times(res, this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun timesAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = times(this, this, bX.f, bY.f, bZ.f, bW.f)
    infix operator fun timesAssign(b: Number) {
        times(this, this, b.f, b.f, b.f, b.f)
    }
    infix operator fun timesAssign(b: Vec4t<out Number>) {
        times(this, this, b.x.f, b.y.f, b.z.f, b.w.f)
    }


    operator fun div(b: Number) = div(Vec4(), this, b.f, b.f, b.f, b.f)
    operator fun div(b: Vec4t<out Number>) = div(Vec4(), this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun div(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4 = Vec4()) = div(res, this, bX.f, bY.f, bZ.f, bW.f)
    fun div(b: Number, res: Vec4 = Vec4()) = div(res, this, b.f, b.f, b.f, b.f)
    fun div(b: Vec4t<out Number>, res: Vec4 = Vec4()) = div(res, this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun divAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = div(this, this, bX.f, bY.f, bZ.f, bW.f)
    infix operator fun divAssign(b: Number) {
        div(this, this, b.f, b.f, b.f, b.f)
    }
    infix operator fun divAssign(b: Vec4t<out Number>) {
        div(this, this, b.x.f, b.y.f, b.z.f, b.w.f)
    }


    operator fun rem(b: Number) = rem(Vec4(), this, b.f, b.f, b.f, b.f)
    operator fun rem(b: Vec4t<out Number>) = rem(Vec4(), this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun rem(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4 = Vec4()) = rem(res, this, bX.f, bY.f, bZ.f, bW.f)
    fun rem(b: Number, res: Vec4 = Vec4()) = rem(res, this, b.f, b.f, b.f, b.f)
    fun rem(b: Vec4t<out Number>, res: Vec4 = Vec4()) = rem(res, this, b.x.f, b.y.f, b.z.f, b.w.f)

    fun remAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = rem(this, this, bX.f, bY.f, bZ.f, bW.f)
    infix operator fun remAssign(b: Number) {
        rem(this, this, b.f, b.f, b.f, b.f)
    }
    infix operator fun remAssign(b: Vec4t<out Number>) {
        rem(this, this, b.x.f, b.y.f, b.z.f, b.w.f)
    }


    // -- functions --

    fun length() = glm.length(this)

    @JvmOverloads
    fun normalize(res: Vec4 = Vec4()) = glm.normalize(this, res) // TODO others

    fun normalizeAssign() = glm.normalize(this, this)

    @JvmOverloads
    fun negate(res: Vec4 = Vec4()): Vec4 {
        res.x = -x
        res.y = -y
        res.z = -z
        res.w = -w
        return res
    }

    fun negateAssign() = negate(this)


    override fun equals(other: Any?) = other is Vec4 && this[0] == other[0] && this[1] == other[1] && this[2] == other[2] && this[3] == other[3]
    override fun hashCode() = 31 * (31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()) + w.hashCode()
}