package com.example.data.model

enum class BioscienceSkillType(
    val title: String,
    val subtitle: String,
    val description: String,
    val promptTemplate: String
) {
    MOA_DECONSTRUCTOR(
        title = "Mechanism of Action Deconstructor",
        subtitle = "Molecular target kinetics & signaling cascades",
        description = "Deconstruct cellular pathways, binding affinities, catalytic inhibition, and downstream gene transcription.",
        promptTemplate = "Perform a rigorous scientific Mechanism of Action (MoA) deconstruction. Detail the molecular target, structural kinetics, receptor binding dynamics, signaling cascade interference, downstream cellular consequences, and compare with known target pathways."
    ),
    TRIAL_ENDPOINT_EVALUATOR(
        title = "Clinical Trial Endpoint & Statistical Evaluator",
        subtitle = "PFS, OS, hazard ratios & RECIST criteria",
        description = "Analyze primary vs secondary endpoints, statistical power, patient selection biomarkers, and trial design validity.",
        promptTemplate = "Critique and evaluate the clinical trial endpoints and study design. Analyze the validity of primary endpoints (e.g. PFS, OS, ORR, biomarker surrogate), sample size power, inclusion/exclusion criteria biases, and potential pitfalls in regulatory statistical scrutiny."
    ),
    SAFETY_OFFTARGET_ANALYSIS(
        title = "Safety & Toxicity Signal Deconstructer",
        subtitle = "Immunogenicity, off-target cleavage & adverse events",
        description = "Screen for CRS, neurotoxicity (ICANS), off-target genomic cleavage, liver enzyme flares, and therapeutic window risks.",
        promptTemplate = "Analyze toxicological and safety signals. Specifically assess off-target liabilities, immunogenicity (e.g. anti-drug antibodies), organ toxicity risks (hepatotoxicity, cardiotoxicity), cytopenia/CRS/ICANS risks in advanced therapies, and therapeutic window safety margins."
    ),
    COMPETITIVE_BENCHMARK(
        title = "Cross-Therapy Competitive Intelligence",
        subtitle = "Head-to-head vs Standard-of-Care (SoC)",
        description = "Benchmark therapeutic efficacy, route of administration, resistance mutational escape, and dosing schedules against competitors.",
        promptTemplate = "Conduct a competitive intelligence benchmark. Compare this innovation against incumbent Standard-of-Care (SoC) agents and competing pipeline candidates. Detail differentiation factors, administration convenience (oral vs IV), resistance mutations, and commercial moat."
    ),
    TRANSLATIONAL_REGULATORY_OUTLOOK(
        title = "Translational & Regulatory Roadmap",
        subtitle = "GMP manufacturability & FDA/EMA designations",
        description = "Examine CMC scalability, cold chain needs, Orphan/Breakthrough designations, and clinical trial phase transitions.",
        promptTemplate = "Evaluate the translational medicine and regulatory roadmap. Assess CMC/manufacturing scalability, delivery vector stability, potential expedited FDA/EMA regulatory pathways (Breakthrough Therapy, Fast Track, RMAT, PRIME), and projected milestones to Phase 3/BLA."
    ),
    CUSTOM_INVESTIGATION(
        title = "Bioscience Researcher Copilot",
        subtitle = "Interactive hypothesis testing & literature synthesis",
        description = "Ask any deep biological, clinical, or biochemical question to synthesize multi-database intelligence.",
        promptTemplate = "Provide an expert bioscience investigation synthesizing cutting-edge literature, biochemical principles, and clinical trial evidence."
    )
}
