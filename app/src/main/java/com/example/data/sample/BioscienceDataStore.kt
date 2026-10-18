package com.example.data.sample

import com.example.data.model.ClinicalTrial
import com.example.data.model.JournalArticle

object BioscienceDataStore {

    val journalArticles: List<JournalArticle> = listOf(
        JournalArticle(
            id = "BIO-NAT-2026-01",
            title = "Ultra-Compact CRISPR-Cas12f Epigenetic Editors for In Vivo Hepatic & CNS Gene Repression",
            journal = "Nature Biotechnology",
            impactFactor = 46.9,
            authors = "Dr. Elena Vance, Dr. Markus Thorne et al. (Stanford / Broad Institute)",
            publishDate = "Jan 2026",
            therapeuticArea = "Gene Editing & Rare Diseases",
            molecularTarget = "PCSK9 & MAPT (Tau)",
            modality = "CRISPR-Cas12f / Epigenetic Base Editor",
            abstractText = "Programmable transcriptional repression offers a durable therapeutic strategy without the double-stranded DNA break hazards of conventional nucleases. Here, we engineer a miniaturized Cas12f variant (480 aa) tethered to a modular KRAB-MeCP2 transcriptional silencing complex. Delivered via a single AAV8 vector, the system achieved >91% serum PCSK9 reduction in non-human primates for over 18 months post single infusion, with undetectable off-target cuts across 45 candidate genomic loci.",
            clinicalStage = "Late Preclinical / IND-Enabling",
            keyTakeaways = listOf(
                "Single-AAV delivery overcomes standard Cas9 packaging size constraints (under 4.7 kb).",
                "Durable epigenetic silencing sustained >18 months without genomic cleavage indels.",
                "High specificity profile confirmed via GUIDE-seq and deep sequencing."
            ),
            doi = "10.1038/s41587-025-02104-x",
            isBreakthrough = true
        ),
        JournalArticle(
            id = "BIO-CELL-2026-02",
            title = "Targeted De Novo Degraders (PROTACs) Overcoming Acquired Resistance in KRAS G12D Solid Tumors",
            journal = "Cell",
            impactFactor = 64.5,
            authors = "Dr. Samantha Wei, Dr. Rajesh Nair et al. (Dana-Farber / Harvard)",
            publishDate = "Feb 2026",
            therapeuticArea = "Oncology",
            molecularTarget = "KRAS G12D / VHL E3 Ligase",
            modality = "Heterobifunctional PROTAC Degrader",
            abstractText = "While KRAS G12C inhibitors have established clinical precedent, the more prevalent G12D mutation in pancreatic ductal adenocarcinoma (PDAC) and colorectal cancer has remained refractory. We report DF-904, an orally bioavailable bifunctional small molecule recruiting the VHL ubiquitin ligase to trigger rapid proteasomal degradation of GDP- and GTP-bound KRAS G12D (DC50 = 1.4 nM). In PDAC patient-derived xenografts, DF-904 induced 84% tumor regression without compensatory ERK rebound.",
            clinicalStage = "Phase 1 Candidate",
            keyTakeaways = listOf(
                "Potent DC50 of 1.4 nM degradation across both active GTP and inactive GDP states.",
                "Circumvents typical feedback activation of wild-type KRAS and RTK bypass loops.",
                "Orally bioavailable with excellent pharmacokinetic bioavailability (>62% in rodent models)."
            ),
            doi = "10.1016/j.cell.2025.11.034",
            isBreakthrough = true
        ),
        JournalArticle(
            id = "BIO-STM-2026-03",
            title = "Extrahepatic Organotropic Lipid Nanoparticles (LNP) for Targeted Pulmonary mRNA Therapeutics",
            journal = "Science Translational Medicine",
            impactFactor = 17.1,
            authors = "Dr. Christopher Lee, Dr. Fiona Gallagher et al. (MIT / Johns Hopkins)",
            publishDate = "Jan 2026",
            therapeuticArea = "Pulmonology & mRNA",
            molecularTarget = "CFTR (Cystic Fibrosis Transmembrane Regulator)",
            modality = "SORT-LNP Ionizable Lipid Formulation",
            abstractText = "Systemic LNP administration has historically been confined to passive liver tropism due to apolipoprotein E (ApoE) corona adsorption. By incorporating a supplemental permanently cationic phospholipid derivative (DOTAP) and synthetic fluorinated lipid blends, we tune the surface charge and protein corona composition. In vivo aerosolized and intravenous delivery achieved 78% epithelial cell mRNA translation in bronchial epithelium with negligible liver accumulation.",
            clinicalStage = "Preclinical In-Vivo Validation",
            keyTakeaways = listOf(
                "Selective lung tropism (>85% pulmonary delivery ratio vs <10% liver clearance).",
                "Restores functional chloride transport in human cystic fibrosis bronchial epithelial air-liquid cultures.",
                "Negligible acute pulmonary immunogenicity or alveolar macrophage inflammatory burst."
            ),
            doi = "10.1126/scitranslmed.adk9821",
            isBreakthrough = false
        ),
        JournalArticle(
            id = "BIO-NEJM-2026-04",
            title = "Bispecific T-Cell Engager (BiTE) Targeting Claudin 18.2 in Advanced Gastric and GEJ Adenocarcinoma",
            journal = "New England Journal of Medicine",
            impactFactor = 96.2,
            authors = "Dr. Hiroshi Tanaka, Dr. Laura Rossi et al. (Global Clinical Oncology Consortium)",
            publishDate = "Jan 2026",
            therapeuticArea = "Oncology & Immunotherapy",
            molecularTarget = "Claudin 18.2 (CLDN18.2) x CD3 epsilon",
            modality = "Bispecific Monoclonal Antibody Engager",
            abstractText = "In a global Phase 1/2 trial of CLDN18.2-positive metastatic gastric adenocarcinoma, the half-life extended BiTE AMG-382 demonstrated an unconfirmed overall response rate (ORR) of 48.6% and a median progression-free survival (PFS) of 8.9 months in heavily pretreated patients who had progressed on checkpoint inhibitors. Cytokine Release Syndrome (CRS) occurred in 64% of patients, with Grade 3+ events restricted to 4.2% following step-up priming dosing.",
            clinicalStage = "Phase 2 Ongoing",
            keyTakeaways = listOf(
                "Substantial clinical activity in checkpoint-refractory gastric cancer (48.6% ORR).",
                "Step-up dosing protocol successfully contained Grade 3+ CRS to under 5%.",
                "Strong correlation between CLDN18.2 membrane density and complete metabolic responses."
            ),
            doi = "10.1056/NEJMoa2510293",
            isBreakthrough = true
        ),
        JournalArticle(
            id = "BIO-LANCET-2026-05",
            title = "Allogeneic Dual-Targeting CD19/CD22 CAR-T with Base-Edited Immune Checkpoint Disruption in Refractory SLE",
            journal = "The Lancet",
            impactFactor = 98.4,
            authors = "Dr. Beatrice Dupont, Dr. Simon Vance et al. (Karolinska / Charité)",
            publishDate = "Feb 2026",
            therapeuticArea = "Autoimmune & Cell Therapy",
            molecularTarget = "CD19 + CD22 / TCR / HLA-A / PD-1",
            modality = "Off-the-Shelf Allogeneic CAR-T",
            abstractText = "Systemic Lupus Erythematosus (SLE) characterized by autoantibody deposition and severe organ damage was treated with universal 'off-the-shelf' CAR-T cells. Using cytosine base editors, TRAC and HLA-A were knocked out to eliminate Graft-versus-Host Disease (GvHD) and host rejection. In 14 patients with active nephritis, 100% achieved complete serological remission and normalization of complement levels at 6 months.",
            clinicalStage = "Phase 1b Multi-Center",
            keyTakeaways = listOf(
                "Zero incidence of GvHD across all cohort recipients due to TRAC multiplex editing.",
                "Complete B-cell reset followed by reconstitution of naïve, non-autoreactive B cells.",
                "Rapid reduction of anti-dsDNA titers and proteinuria improvement."
            ),
            doi = "10.1016/S0140-6736(25)02381-9",
            isBreakthrough = true
        ),
        JournalArticle(
            id = "BIO-NATMED-2026-06",
            title = "Tau Microtubule-Binding Region (MTBR-tau243) Biomarker Directed Antisense Oligonucleotide in Early Alzheimer's",
            journal = "Nature Medicine",
            impactFactor = 58.7,
            authors = "Dr. Arthur Pendelton, Dr. Claire Moreau et al. (UCL / Mayo Clinic)",
            publishDate = "Dec 2025",
            therapeuticArea = "Neuroscience",
            molecularTarget = "MAPT Exon 10 / MTBR-tau243",
            modality = "Locked Nucleic Acid (LNA) Antisense Oligo",
            abstractText = "CSF and plasma MTBR-tau243 has emerged as the definitive surrogate for insoluble tau tangle propagation in symptomatic Alzheimer's disease. We evaluated BIIB-080 intrathecal administration guided by real-time tau PET and biofluid biomarker surveillance. In 96 patients with mild cognitive impairment, 60 mg quarterly dosing resulted in a 68% decrease in CSF tau seeds and a 34% attenuation of cognitive decline on the CDR-SB scale.",
            clinicalStage = "Phase 2 Completed",
            keyTakeaways = listOf(
                "Direct correlation between MTBR-tau243 suppression and slowing of neurodegeneration.",
                "Favorable safety profile with mild transient post-lumbar puncture headache as primary AE.",
                "Validates fluid biomarkers as predictive enrichment criteria for neurodegenerative trials."
            ),
            doi = "10.1038/s41591-025-03482-1",
            isBreakthrough = false
        )
    )

    val clinicalTrials: List<ClinicalTrial> = listOf(
        ClinicalTrial(
            nctId = "NCT06284910",
            title = "A Phase 3, Randomized, Double-Blind Study of Oral KRAS G12D Inhibitor (RMC-9805) vs Gemcitabine/Nab-Paclitaxel in First-Line Metastatic Pancreatic Ductal Adenocarcinoma",
            phase = "Phase 3",
            status = "Recruiting",
            sponsor = "Revolution Medicines Inc. / Oncology Cooperative Group",
            condition = "Metastatic Pancreatic Ductal Adenocarcinoma",
            intervention = "RMC-9805 (Oral 400mg QD) vs Gemcitabine + Nab-Paclitaxel",
            primaryEndpoint = "Overall Survival (OS) and Progression-Free Survival (PFS)",
            enrollmentCount = 680,
            startDate = "March 2025",
            completionDate = "December 2027",
            therapeuticArea = "Oncology",
            biomarkers = listOf("KRAS G12D Mutation (Tissue or ctDNA)", "MSS (Microsatellite Stable)"),
            studyDesign = "Interventional, Randomized 1:1, Double-Blind, Multi-Center International",
            detailedSummary = "Evaluating the superior efficacy of first-in-class mutant-selective covalent KRAS G12D inhibitor. Secondary endpoints include Objective Response Rate (ORR), Duration of Response (DoR), and Patient-Reported Quality of Life scores. Stratified by ECOG performance status and liver metastatic burden."
        ),
        ClinicalTrial(
            nctId = "NCT05912440",
            title = "Phase 1/2 Open-Label Trial of In Vivo LNP-CRISPR Base Editor (NTLA-3001) Targeting SerpinA1 for Alpha-1 Antitrypsin Deficiency Associated Liver Disease",
            phase = "Phase 1/2",
            status = "Active, not recruiting",
            sponsor = "Intellia Therapeutics",
            condition = "Alpha-1 Antitrypsin Deficiency (AATD) Liver Disease",
            intervention = "NTLA-3001 (Single Intravenous Lipid Nanoparticle Infusion)",
            primaryEndpoint = "Safety, Tolerability, and Change from Baseline in Mutant Z-AAT Polymer Liver Burden",
            enrollmentCount = 36,
            startDate = "October 2024",
            completionDate = "June 2026",
            therapeuticArea = "Gene Editing & Liver Disease",
            biomarkers = listOf("Homozygous Pi*ZZ Genotype", "Circulating Z-AAT Monomer/Polymer Ratio"),
            studyDesign = "Sequential Dose-Escalation followed by Dose-Expansion Cohort",
            detailedSummary = "First-in-human systemically infused in vivo adenine base editor formulated in targeted lipid nanoparticles to correct the Pi*Z pathogenic mutation in hepatocytes, preserving endogenous AAT expression while preventing toxic proteotoxic polymer aggregation."
        ),
        ClinicalTrial(
            nctId = "NCT06443219",
            title = "Phase 2 Evaluation of Personalized Neoantigen mRNA Vaccine (mRNA-4157) Combined with Pembrolizumab in Resected High-Risk Clear Cell Renal Cell Carcinoma",
            phase = "Phase 2",
            status = "Recruiting",
            sponsor = "Moderna Therapeutics & Merck Sharp & Dohme",
            condition = "Clear Cell Renal Cell Carcinoma (ccRCC)",
            intervention = "mRNA-4157 (Up to 34 patient-specific neoantigens, 1mg IM) + Pembrolizumab (200mg IV Q3W)",
            primaryEndpoint = "Disease-Free Survival (DFS) assessed by Independent Review Committee",
            enrollmentCount = 290,
            startDate = "January 2025",
            completionDate = "August 2028",
            therapeuticArea = "Immuno-Oncology & Cancer Vaccines",
            biomarkers = listOf("Tumor Mutational Burden (TMB)", "HLA Class I Typing", "PD-L1 CPS"),
            studyDesign = "Randomized 1:1 Active-Controlled Adjuvant Trial",
            detailedSummary = "Assessing whether adding algorithmic AI-designed neoepitope mRNA vaccination to PD-1 checkpoint blockade generates robust polyclonal CD8+ and CD4+ T-cell neoantigen specific immunity, reducing distant recurrence in post-nephrectomy renal cell cancer."
        ),
        ClinicalTrial(
            nctId = "NCT05822981",
            title = "Phase 3 Pivotal Trial of Intrathecal Microtubule-Binding Tau Antisense Oligonucleotide in Early Alzheimer's Disease and Mild Cognitive Impairment",
            phase = "Phase 3",
            status = "Recruiting",
            sponsor = "Biogen & Ionis Pharmaceuticals",
            condition = "Early Symptomatic Alzheimer's Disease",
            intervention = "BIIB080 (60mg Intrathecal Bolus Q12W) vs Placebo",
            primaryEndpoint = "Change from baseline in Clinical Dementia Rating-Sum of Boxes (CDR-SB) at Week 76",
            enrollmentCount = 1200,
            startDate = "August 2024",
            completionDate = "November 2027",
            therapeuticArea = "Neuroscience",
            biomarkers = listOf("Amyloid-beta Positive (PET or CSF)", "Elevated CSF p-Tau217 / MTBR-tau243"),
            studyDesign = "Quadruple-Blind, Parallel Assignment, Global Phase 3",
            detailedSummary = "Evaluating disease-modifying potential of direct tau mRNA knockdown to halt downstream neurofibrillary tangle progression across the neocortex in early AD patients, measured by volumetric MRI, cognitive battery, and biofluid biomarker cascades."
        ),
        ClinicalTrial(
            nctId = "NCT06019876",
            title = "Phase 1/2 Dose Escalation Study of Claudin 6 (CLDN6) CAR-T Engineered with Membrane-Bound IL-7 and CCL19 in Ovarian & Testicular Solid Cancers",
            phase = "Phase 1/2",
            status = "Recruiting",
            sponsor = "BioNTech SE",
            condition = "Relapsed/Refractory Ovarian Cancer & Germ Cell Tumors",
            intervention = "BNT211 (CLDN6-CAR-T cells +/- CAR-T Cell-Amplifying RNA Vaccine CARVac)",
            primaryEndpoint = "Dose-Limiting Toxicity (DLT), Maximum Tolerated Dose (MTD), and Objective Response Rate",
            enrollmentCount = 84,
            startDate = "May 2024",
            completionDate = "September 2026",
            therapeuticArea = "Cell Therapy & Oncology",
            biomarkers = listOf("CLDN6 Expression >= 50% by IHC", "Mesothelin / CD3+ Infiltrate"),
            studyDesign = "Interventional Open-Label Single-Arm Adaptive Escalation",
            detailedSummary = "Investigating next-generation CAR-T designed for solid tumors, paired with a systemic RNA vaccine (CARVac) that repetitively stimulates CAR-T expansion in lymphatic tissues, overcoming immunosuppressive tumor microenvironment barriers."
        ),
        ClinicalTrial(
            nctId = "NCT06155982",
            title = "Phase 2 Multicenter Study of BCMA / GPRC5D Dual-Targeting Chimeric Antigen Receptor T-Cells in Triple-Class Refractory Multiple Myeloma",
            phase = "Phase 2",
            status = "Active, not recruiting",
            sponsor = "Memorial Sloan Kettering & Legend Biotech",
            condition = "Relapsed and Refractory Multiple Myeloma (RRMM)",
            intervention = "Dual-BiTE CAR-T (Single Autologous Infusion)",
            primaryEndpoint = "Overall Response Rate (ORR) and Minimal Residual Disease (MRD) Negativity (10^-5)",
            enrollmentCount = 92,
            startDate = "November 2024",
            completionDate = "May 2026",
            therapeuticArea = "Hematology & Oncology",
            biomarkers = listOf("BCMA Expression", "GPRC5D Expression", "High-Risk Cytogenetics [del(17p), t(4;14)]"),
            studyDesign = "Non-Randomized Phase 2 Expansion Cohort",
            detailedSummary = "Mitigating single-target antigen escape (loss of BCMA) by deploying a tandem bispecific CAR architecture recognizing both BCMA and the G-protein coupled orphan receptor GPRC5D on clonal plasma cells."
        )
    )

    val therapeuticAreas = listOf(
        "All Areas",
        "Oncology",
        "Gene Editing & Rare Diseases",
        "Cell Therapy & Autoimmune",
        "Neuroscience",
        "Pulmonology & mRNA",
        "Hematology & Oncology"
    )

    val trialPhases = listOf(
        "All Phases",
        "Phase 1",
        "Phase 1/2",
        "Phase 2",
        "Phase 3"
    )

    val recruitmentStatuses = listOf(
        "All Statuses",
        "Recruiting",
        "Active, not recruiting",
        "Completed"
    )
}
