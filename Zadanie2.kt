package Lista2ZJP
/**
 *
 * @author Jakub Smoliński.
 * @param indentifer
 * @param data
 * @return DNASequence
 * @return RNASequence
 * @return ProteinSequence
 * @throws IllegalArgumentException jeśli pozycja w sekwencji jest nieprawidłowa
 */

/**
 * Klasa reprezentująca sekwencję DNA.
 * @property identifier identyfikator sekwencji
 * @property data sekwencja zasad DNA
 */
class DNASequence(val identifier: String, private val data: String) {
    enum class Nucleotide {
        A, C, G, T
    }

    val length: Int
        get() = data.length

    override fun toString(): String {
        return ">$identifier\n$data"
    }

    fun mutate(position: Int, value: Nucleotide): DNASequence {
        if (position < 0 || position >= data.length) {
            throw IllegalArgumentException("Invalid position: $position")
        }
        val nowy = data.substring(0, position) + value.name + data.substring(position + 1)
        return DNASequence(identifier, nowy)
    }

    fun findMotif(motif: String): Int {
        return data.indexOf(motif)
    }

    fun complement(): DNASequence {
        val complementMap = mapOf('A' to 'T', 'T' to 'A', 'C' to 'G', 'G' to 'C')
        val complementData = data.map { complementMap[it] ?: it }.joinToString("")
        return DNASequence(identifier, complementData)
    }

    fun transcribe(): RNASequence {
        val rnaData = data.replace('T', 'U')
        return RNASequence(identifier, rnaData)
    }
}
/**
 * Klasa reprezentująca sekwencję RNA.
 * @property identifier identyfikator sekwencji
 * @property data sekwencja zasad RNA
 */
class RNASequence(val identifier: String, private val data: String) {
    enum class Nucleotide {
        A, C, G, U
    }

    val length: Int
        get() = data.length

    override fun toString(): String {
        return ">$identifier\n$data"
    }

    fun mutate(position: Int, value: Nucleotide): RNASequence {
        if (position < 0 || position >= data.length) {
            throw IllegalArgumentException("Invalid position: $position")
        }
        val newData = data.substring(0, position) + value.name + data.substring(position + 1)
        return RNASequence(identifier, newData)
    }

    fun findMotif(motif: String): Int {
        return data.indexOf(motif)
    }
}
/**
 * Klasa reprezentująca sekwencję białka.
 * @property identifier identyfikator sekwencji
 * @property data sekwencja białka
 */
class ProteinSequence(val identifier: String, private val data: String) {
    enum class AminoAcid {
        A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y
    }

    val length: Int
        get() = data.length

    override fun toString(): String {
        return ">$identifier\n$data"
    }

    fun mutate(position: Int, value: AminoAcid): ProteinSequence {
        if (position < 0 || position >= data.length) {
            throw IllegalArgumentException("Invalid position: $position")
        }
        val newData = data.substring(0, position) + value.name + data.substring(position + 1)
        return ProteinSequence(identifier, newData)
    }

    fun findMotif(motif: String): Int {
        return data.indexOf(motif)
    }
}
fun main() {
    val dnaSeq = DNASequence("DNA1", "ATCGATCG")
    println("DNA Sequence:")
    println(dnaSeq)
    println("Length: ${dnaSeq.length}")
    println("Mutated DNA:")
    val mutatedDNA = dnaSeq.mutate(4, DNASequence.Nucleotide.G)
    println(mutatedDNA)
    println("Finding motif 'ATC': ${dnaSeq.findMotif("ATC")}")

    val rnaSeq = RNASequence("RNA1", "ATCGATCG")
    println("\nRNA Sequence:")
    println(rnaSeq)
    println("Length: ${rnaSeq.length}")
    println("Mutated RNA:")
    val mutatedRNA = rnaSeq.mutate(4, RNASequence.Nucleotide.G)
    println(mutatedRNA)
    println("Finding motif 'AUC': ${rnaSeq.findMotif("AUC")}")

    val proteinSeq = ProteinSequence("Protein1", "ATCGATCG")
    println("\nProtein Sequence:")
    println(proteinSeq)
    println("Length: ${proteinSeq.length}")
    println("Mutated Protein:")
    val mutatedProtein = proteinSeq.mutate(4, ProteinSequence.AminoAcid.W)
    println(mutatedProtein)
    println("Finding motif 'DEF': ${proteinSeq.findMotif("DEF")}")
}