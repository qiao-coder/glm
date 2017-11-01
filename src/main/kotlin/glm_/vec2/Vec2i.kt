package glm_.vec2

import glm_.*
import glm_.vec2.operators.opVec2i
import glm_.vec3.Vec3bool
import glm_.vec3.Vec3t
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4t
import java.nio.*

/**
 * Created bY GBarbieri on 06.10.2016.
 */

class Vec2i(x: Int, y: Int) : Vec2t<Int>(x, y) {

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor() : this(0)

    constructor(v: Vec2t<out Number>) : this(v.x, v.y)
    constructor(v: Vec3t<out Number>) : this(v.x, v.y)
    constructor(v: Vec4t<out Number>) : this(v.x, v.y)

    constructor(v: Vec2bool) : this(v.x.i, v.y.i)
    constructor(v: Vec3bool) : this(v.x.i, v.y.i)
    constructor(v: Vec4bool) : this(v.x.i, v.y.i)

    constructor(bytes: ByteArray, index: Int = 0, oneByteOneInt: Boolean = false, bigEndian: Boolean = true) : this(
            if (oneByteOneInt) bytes[index].i else bytes.getInt(index, bigEndian),
            if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES, bigEndian))

    constructor(chars: CharArray, index: Int = 0) : this(chars[index].i, chars[index + 1].i)
    constructor(shorts: ShortArray, index: Int = 0) : this(shorts[index], shorts[index + 1])
    constructor(ints: IntArray, index: Int = 0) : this(ints[index], ints[index + 1])
    constructor(longs: LongArray, index: Int = 0) : this(longs[index], longs[index + 1])
    constructor(floats: FloatArray, index: Int = 0) : this(floats[index], floats[index + 1])
    constructor(doubles: DoubleArray, index: Int = 0) : this(doubles[index], doubles[index + 1])
    constructor(booleans: BooleanArray, index: Int = 0) : this(booleans[index].i, booleans[index + 1].i)

    constructor(numbers: Array<out Number>, index: Int = 0) : this(numbers[index], numbers[index + 1])
    constructor(chars: Array<Char>, index: Int = 0) : this(chars[index].i, chars[index + 1].i)
    constructor(booleans: Array<Boolean>, index: Int = 0) : this(booleans[index].i, booleans[index + 1].i)

    constructor(list: List<Any>, index: Int = 0) : this(list[index].toInt, list[index + 1].toInt)

    constructor(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneInt: Boolean = false) : this(
            if (oneByteOneInt) bytes[index].i else bytes.getInt(index),
            if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES))

    constructor(chars: CharBuffer, index: Int = chars.position()) : this(chars[index].i, chars[index + 1].i)
    constructor(shorts: ShortBuffer, index: Int = shorts.position()) : this(shorts[index], shorts[index + 1])
    constructor(ints: IntBuffer, index: Int = ints.position()) : this(ints[index], ints[index + 1])
    constructor(longs: LongBuffer, index: Int = longs.position()) : this(longs[index], longs[index + 1])
    constructor(floats: FloatBuffer, index: Int = floats.position()) : this(floats[index], floats[index + 1])
    constructor(doubles: DoubleBuffer, index: Int = doubles.position()) : this(doubles[index], doubles[index + 1])

    constructor(s: Number) : this(s, s)
    constructor(x: Number, y: Number) : this(x.i, y.i)
    constructor(x: IntBuffer, y: IntBuffer) : this(x[0], y[0]) // TODO others


    fun set(bytes: ByteArray, index: Int = 0, oneByteOneInt: Boolean = false, bigEndian: Boolean = true) {
        x = if (oneByteOneInt) bytes[index].i else bytes.getInt(index, bigEndian)
        y = if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES, bigEndian)
    }

    fun set(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneInt: Boolean = false) {
        x = if (oneByteOneInt) bytes[index].i else bytes.getInt(index)
        y = if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES)
    }


    fun put(x: Int, y: Int): Vec2i {
        this.x = x
        this.y = y
        return this
    }

    override fun put(x: Number, y: Number): Vec2i {
        this.x = x.i
        this.y = y.i
        return this
    }


    infix fun to(ints: IntArray) = to(ints, 0)
    fun to(ints: IntArray, index: Int): IntArray {
        ints[index] = x
        ints[index + 1] = y
        return ints
    }

    infix fun to(ints: IntBuffer) = to(ints, 0)
    fun to(ints: IntBuffer, index: Int): IntBuffer {
        ints[index] = x
        ints[index + 1] = y
        return ints
    }

    infix fun to(bytes: ByteBuffer) = to(bytes, bytes.position())
    fun to(bytes: ByteBuffer, offset: Int): ByteBuffer {
        bytes.putInt(offset, x)
        bytes.putInt(offset + Int.BYTES, y)
        return bytes
    }


    // -- Component accesses --

    override operator fun get(i: Int) = when (i) {
        0 -> x
        1 -> y
        else -> throw ArrayIndexOutOfBoundsException()
    }

    operator fun set(i: Int, s: Int) = when (i) {
        0 -> x = s
        1 -> y = s
        else -> throw ArrayIndexOutOfBoundsException()
    }

    operator fun set(i: Int, s: Number) = when (i) {
        0 -> x = s.i
        1 -> y = s.i
        else -> throw ArrayIndexOutOfBoundsException()
    }

    // TODO
    fun comptimes() = x * y


    companion object : opVec2i() {
        @JvmField
        val length = 2
        @JvmField
        val size = length * Int.BYTES
    }


    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Vec2i(-x, -y)

    // -- Increment main.and decrement operators --

    operator fun inc() = plus(Vec2i(), this, 1, 1)
    infix fun inc(res: Vec2i) = plus(res, this, 1, 1)
    fun incAssign() = plus(this, this, 1, 1)


    operator fun dec() = minus(Vec2i(), this, 1, 1)
    infix fun dec(res: Vec2i) = minus(res, this, 1, 1)
    fun decAssign() = minus(this, this, 1, 1)


    // -- Specific binary arithmetic operators --

    infix operator fun plus(b: Int) = plus(Vec2i(), this, b, b)
    infix operator fun plus(b: Vec2i) = plus(Vec2i(), this, b.x, b.y)

    @JvmOverloads
    fun plus(bX: Int, bY: Int, res: Vec2i = Vec2i()) = plus(res, this, bX, bY)

    fun plus(b: Int, res: Vec2i) = plus(res, this, b, b)
    fun plus(b: Vec2i, res: Vec2i) = plus(res, this, b.x, b.y)

    fun plusAssign(bX: Int, bY: Int) = plus(this, this, bX, bY)
    infix operator fun plusAssign(b: Int) {
        plus(this, this, b, b)
    }
    infix operator fun plusAssign(b: Vec2i) {
        plus(this, this, b.x, b.y)
    }


    infix operator fun minus(b: Int) = minus(Vec2i(), this, b, b)
    infix operator fun minus(b: Vec2i) = minus(Vec2i(), this, b.x, b.y)

    @JvmOverloads
    fun minus(bX: Int, bY: Int, res: Vec2i = Vec2i()) = minus(res, this, bX, bY)

    fun minus(b: Int, res: Vec2i) = minus(res, this, b, b)
    fun minus(b: Vec2i, res: Vec2i) = minus(res, this, b.x, b.y)

    fun minusAssign(bX: Int, bY: Int) = minus(this, this, bX, bY)
    infix operator fun minusAssign(b: Int) {
        minus(this, this, b, b)
    }
    infix operator fun minusAssign(b: Vec2i) {
        minus(this, this, b.x, b.y)
    }


    infix operator fun times(b: Int) = times(Vec2i(), this, b, b)
    infix operator fun times(b: Vec2i) = times(Vec2i(), this, b.x, b.y)

    @JvmOverloads
    fun times(bX: Int, bY: Int, res: Vec2i = Vec2i()) = times(res, this, bX, bY)

    fun times(b: Int, res: Vec2i) = times(res, this, b, b)
    fun times(b: Vec2i, res: Vec2i) = times(res, this, b.x, b.y)

    fun timesAssign(bX: Int, bY: Int) = times(this, this, bX, bY)
    infix operator fun timesAssign(b: Int) {
        times(this, this, b, b)
    }
    infix operator fun timesAssign(b: Vec2i) {
        times(this, this, b.x, b.y)
    }


    infix operator fun div(b: Int) = div(Vec2i(), this, b, b)
    infix operator fun div(b: Vec2i) = div(Vec2i(), this, b.x, b.y)

    @JvmOverloads
    fun div(bX: Int, bY: Int, res: Vec2i = Vec2i()) = div(res, this, bX, bY)

    fun div(b: Int, res: Vec2i) = div(res, this, b, b)
    fun div(b: Vec2i, res: Vec2i) = div(res, this, b.x, b.y)

    fun divAssign(bX: Int, bY: Int) = div(this, this, bX, bY)
    infix operator fun divAssign(b: Int) {
        div(this, this, b, b)
    }
    infix operator fun divAssign(b: Vec2i) {
        div(this, this, b.x, b.y)
    }


    infix operator fun rem(b: Int) = rem(Vec2i(), this, b, b)
    infix operator fun rem(b: Vec2i) = rem(Vec2i(), this, b.x, b.y)

    @JvmOverloads
    fun rem(bX: Int, bY: Int, res: Vec2i = Vec2i()) = rem(res, this, bX, bY)

    fun rem(b: Int, res: Vec2i) = rem(res, this, b, b)
    fun rem(b: Vec2i, res: Vec2i) = rem(res, this, b.x, b.y)

    fun remAssign(bX: Int, bY: Int) = rem(this, this, bX, bY)
    infix operator fun remAssign(b: Int) {
        rem(this, this, b, b)
    }
    infix operator fun remAssign(b: Vec2i) {
        rem(this, this, b.x, b.y)
    }


    // -- Generic binary arithmetic operators --

    infix operator fun plus(b: Number) = plus(Vec2i(), this, b.i, b.i)
    infix operator fun plus(b: Vec2t<out Number>) = plus(Vec2i(), this, b.x.i, b.y.i)

    @JvmOverloads
    fun plus(bX: Number, bY: Number, res: Vec2i = Vec2i()) = plus(res, this, bX.i, bY.i)

    fun plus(b: Number, res: Vec2i) = plus(res, this, b.i, b.i)
    fun plus(b: Vec2t<out Number>, res: Vec2i) = plus(res, this, b.x.i, b.y.i)

    fun plusAssign(bX: Number, bY: Number) = plus(this, this, bX.i, bY.i)
    infix operator fun plusAssign(b: Number) {
        plus(this, this, b.i, b.i)
    }
    infix operator fun plusAssign(b: Vec2t<out Number>) {
        plus(this, this, b.x.i, b.y.i)
    }


    infix operator fun minus(b: Number) = minus(Vec2i(), this, b.i, b.i)
    infix operator fun minus(b: Vec2t<out Number>) = minus(Vec2i(), this, b.x.i, b.y.i)

    @JvmOverloads
    fun minus(bX: Number, bY: Number, res: Vec2i = Vec2i()) = minus(res, this, bX.i, bY.i)

    fun minus(b: Number, res: Vec2i) = minus(res, this, b.i, b.i)
    fun minus(b: Vec2t<out Number>, res: Vec2i) = minus(res, this, b.x.i, b.y.i)

    fun minusAssign(bX: Number, bY: Number) = minus(this, this, bX.i, bY.i)
    infix operator fun minusAssign(b: Number) {
        minus(this, this, b.i, b.i)
    }
    infix operator fun minusAssign(b: Vec2t<out Number>) {
        minus(this, this, b.x.i, b.y.i)
    }


    infix operator fun times(b: Number) = times(Vec2i(), this, b.i, b.i)
    infix operator fun times(b: Vec2t<out Number>) = times(Vec2i(), this, b.x.i, b.y.i)

    @JvmOverloads
    fun times(bX: Number, bY: Number, res: Vec2i = Vec2i()) = times(res, this, bX.i, bY.i)

    fun times(b: Number, res: Vec2i) = times(res, this, b.i, b.i)
    fun times(b: Vec2t<out Number>, res: Vec2i) = times(res, this, b.x.i, b.y.i)

    fun timesAssign(bX: Number, bY: Number) = times(this, this, bX.i, bY.i)
    infix operator fun timesAssign(b: Number) {
        times(this, this, b.i, b.i)
    }
    infix operator fun timesAssign(b: Vec2t<out Number>) {
        times(this, this, b.x.i, b.y.i)
    }


    infix operator fun div(b: Number) = div(Vec2i(), this, b.i, b.i)
    infix operator fun div(b: Vec2t<out Number>) = div(Vec2i(), this, b.x.i, b.y.i)

    @JvmOverloads
    fun div(bX: Number, bY: Number, res: Vec2i = Vec2i()) = div(res, this, bX.i, bY.i)

    fun div(b: Number, res: Vec2i) = div(res, this, b.i, b.i)
    fun div(b: Vec2t<out Number>, res: Vec2i) = div(res, this, b.x.i, b.y.i)

    fun divAssign(bX: Number, bY: Number) = div(this, this, bX.i, bY.i)
    infix operator fun divAssign(b: Number) {
        div(this, this, b.i, b.i)
    }
    infix operator fun divAssign(b: Vec2t<out Number>) {
        div(this, this, b.x.i, b.y.i)
    }


    infix operator fun rem(b: Number) = rem(Vec2i(), this, b.i, b.i)
    infix operator fun rem(b: Vec2t<out Number>) = rem(Vec2i(), this, b.x.i, b.y.i)

    @JvmOverloads
    fun rem(bX: Number, bY: Number, res: Vec2i = Vec2i()) = rem(res, this, bX.i, bY.i)

    fun rem(b: Number, res: Vec2i) = rem(res, this, b.i, b.i)
    fun rem(b: Vec2t<out Number>, res: Vec2i) = rem(res, this, b.x.i, b.y.i)

    fun remAssign(bX: Number, bY: Number) = rem(this, this, bX.i, bY.i)
    infix operator fun remAssign(b: Number) {
        rem(this, this, b.i, b.i)
    }
    infix operator fun remAssign(b: Vec2t<out Number>) {
        rem(this, this, b.x.i, b.y.i)
    }


    // -- Specific bitwise operators --

    infix fun and(b: Int) = and(Vec2i(), this, b, b)
    infix fun and(b: Vec2i) = and(Vec2i(), this, b.x, b.y)

    fun and(b: Int, res: Vec2i) = and(res, this, b, b)
    fun and(b: Vec2i, res: Vec2i) = and(res, this, b.x, b.y)
    @JvmOverloads
    fun and(bX: Int, bY: Int, res: Vec2i = Vec2i()) = and(res, this, bX, bY)

    infix fun andAssign(b: Int) = and(this, this, b, b)
    infix fun andAssign(b: Vec2i) = and(this, this, b.x, b.y)
    fun andAssign(bX: Int, bY: Int) = and(this, this, bX, bY)


    infix fun or(b: Int) = or(Vec2i(), this, b, b)
    infix fun or(b: Vec2i) = or(Vec2i(), this, b.x, b.y)

    fun or(b: Int, res: Vec2i) = or(res, this, b, b)
    fun or(b: Vec2i, res: Vec2i) = or(res, this, b.x, b.y)
    @JvmOverloads
    fun or(bX: Int, bY: Int, res: Vec2i = Vec2i()) = or(res, this, bX, bY)

    infix fun orAssign(b: Int) = or(this, this, b, b)
    infix fun orAssign(b: Vec2i) = or(this, this, b.x, b.y)
    fun orAssign(bX: Int, bY: Int) = or(this, this, bX, bY)


    infix fun xor(b: Int) = xor(Vec2i(), this, b, b)
    infix fun xor(b: Vec2i) = xor(Vec2i(), this, b.x, b.y)

    fun xor(b: Int, res: Vec2i) = xor(res, this, b, b)
    fun xor(b: Vec2i, res: Vec2i) = xor(res, this, b.x, b.y)
    @JvmOverloads
    fun xor(bX: Int, bY: Int, res: Vec2i = Vec2i()) = xor(res, this, bX, bY)

    infix fun xorAssign(b: Int) = xor(this, this, b, b)
    infix fun xorAssign(b: Vec2i) = xor(this, this, b.x, b.y)
    fun xorAssign(bX: Int, bY: Int) = xor(this, this, bX, bY)


    infix fun shl(b: Int) = shl(Vec2i(), this, b, b)
    infix fun shl(b: Vec2i) = shl(Vec2i(), this, b.x, b.y)

    fun shl(b: Int, res: Vec2i) = shl(res, this, b, b)
    fun shl(b: Vec2i, res: Vec2i) = shl(res, this, b.x, b.y)
    @JvmOverloads
    fun shl(bX: Int, bY: Int, res: Vec2i = Vec2i()) = shl(res, this, bX, bY)

    infix fun shlAssign(b: Int) = shl(this, this, b, b)
    infix fun shlAssign(b: Vec2i) = shl(this, this, b.x, b.y)
    fun shlAssign(bX: Int, bY: Int) = shl(this, this, bX, bY)


    infix fun shr(b: Int) = shr(Vec2i(), this, b, b)
    infix fun shr(b: Vec2i) = shr(Vec2i(), this, b.x, b.y)

    fun shr(b: Int, res: Vec2i) = shr(res, this, b, b)
    fun shr(b: Vec2i, res: Vec2i) = shr(res, this, b.x, b.y)
    @JvmOverloads
    fun shr(bX: Int, bY: Int, res: Vec2i = Vec2i()) = shr(res, this, bX, bY)

    infix fun shrAssign(b: Int) = shr(this, this, b, b)
    infix fun shrAssign(b: Vec2i) = shr(this, this, b.x, b.y)
    fun shrAssign(bX: Int, bY: Int) = shr(this, this, bX, bY)


    // TODO fill & others
    infix fun ushr(b: Int) = ushr(Vec2i(), this, b, b)


    @JvmOverloads
    fun inv(res: Vec2i = Vec2i()) = inv(res, this)

    fun invAssign() = inv(this, this)


    // -- Generic bitwise operators --

    infix fun and(b: Number) = and(Vec2i(), this, b.i, b.i)
    infix fun and(b: Vec2t<out Number>) = and(Vec2i(), this, b.x.i, b.y.i)

    fun and(b: Number, res: Vec2i) = and(res, this, b.i, b.i)
    fun and(b: Vec2t<out Number>, res: Vec2i) = and(res, this, b.x.i, b.y.i)
    @JvmOverloads
    fun and(bX: Number, bY: Number, res: Vec2i = Vec2i()) = and(res, this, bX.i, bY.i)

    infix fun andAssign(b: Number) = and(this, this, b.i, b.i)
    infix fun andAssign(b: Vec2t<out Number>) = and(this, this, b.x.i, b.y.i)
    fun andAssign(bX: Number, bY: Number) = and(this, this, bX.i, bY.i)


    infix fun or(b: Number) = or(Vec2i(), this, b.i, b.i)
    infix fun or(b: Vec2t<out Number>) = or(Vec2i(), this, b.x.i, b.y.i)

    fun or(b: Number, res: Vec2i) = or(res, this, b.i, b.i)
    fun or(b: Vec2t<out Number>, res: Vec2i) = or(res, this, b.x.i, b.y.i)
    @JvmOverloads
    fun or(bX: Number, bY: Number, res: Vec2i = Vec2i()) = or(res, this, bX.i, bY.i)

    infix fun orAssign(b: Number) = or(this, this, b.i, b.i)
    infix fun orAssign(b: Vec2t<out Number>) = or(this, this, b.x.i, b.y.i)
    fun orAssign(bX: Number, bY: Number) = or(this, this, bX.i, bY.i)


    infix fun xor(b: Number) = xor(Vec2i(), this, b.i, b.i)
    infix fun xor(b: Vec2t<out Number>) = xor(Vec2i(), this, b.x.i, b.y.i)

    fun xor(b: Number, res: Vec2i) = xor(res, this, b.i, b.i)
    fun xor(b: Vec2t<out Number>, res: Vec2i) = xor(res, this, b.x.i, b.y.i)
    @JvmOverloads
    fun xor(bX: Number, bY: Number, res: Vec2i = Vec2i()) = xor(res, this, bX.i, bY.i)

    infix fun xorAssign(b: Number) = xor(this, this, b.i, b.i)
    infix fun xorAssign(b: Vec2t<out Number>) = xor(this, this, b.x.i, b.y.i)
    fun xorAssign(bX: Number, bY: Number) = xor(this, this, bX.i, bY.i)


    infix fun shl(b: Number) = shl(Vec2i(), this, b.i, b.i)
    infix fun shl(b: Vec2t<out Number>) = shl(Vec2i(), this, b.x.i, b.y.i)

    fun shl(b: Number, res: Vec2i) = shl(res, this, b.i, b.i)
    fun shl(b: Vec2t<out Number>, res: Vec2i) = shl(res, this, b.x.i, b.y.i)
    @JvmOverloads
    fun shl(bX: Number, bY: Number, res: Vec2i = Vec2i()) = shl(res, this, bX.i, bY.i)

    infix fun shlAssign(b: Number) = shl(this, this, b.i, b.i)
    infix fun shlAssign(b: Vec2t<out Number>) = shl(this, this, b.x.i, b.y.i)
    fun shlAssign(bX: Number, bY: Number) = shl(this, this, bX.i, bY.i)


    infix fun shr(b: Number) = shr(Vec2i(), this, b.i, b.i)
    infix fun shr(b: Vec2t<out Number>) = shr(Vec2i(), this, b.x.i, b.y.i)

    fun shr(b: Number, res: Vec2i) = shr(res, this, b.i, b.i)
    fun shr(b: Vec2t<out Number>, res: Vec2i) = shr(res, this, b.x.i, b.y.i)
    @JvmOverloads
    fun shr(bX: Number, bY: Number, res: Vec2i = Vec2i()) = shr(res, this, bX.i, bY.i)

    infix fun shrAssign(b: Number) = shr(this, this, b.i, b.i)
    infix fun shrAssign(b: Vec2t<out Number>) = shr(this, this, b.x.i, b.y.i)
    fun shrAssign(bX: Number, bY: Number) = shr(this, this, bX.i, bY.i)


    infix fun lessThan(i: Int) = x < i && y < i
    infix fun lessThanEqual(i: Int) = x <= i && y <= i
    infix fun equal(i: Int) = x == i && y == i
    infix fun notEqual(i: Int) = x != i && y != i
    infix fun greaterThan(i: Int) = x > i && y > i
    infix fun greaterThanEqual(i: Int) = x >= i && y >= i


    override fun equals(other: Any?) = other is Vec2i && this[0] == other[0] && this[1] == other[1]
    override fun hashCode() = 31 * x.hashCode() + y.hashCode()
}