package pl.edu.pjwstk.simulator.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Station {
    GDANSK_GLOWNY,
    GDANSK_STOCZNIA,
    GDANSK_POLITECHNIKA,
    GDANSK_WRZESZCZ,
    GDANSK_ZASPA,
    GDANSK_PRZYMORZE,
    GDANSK_OLIWA,
    GDANSK_ZABIANKA,
    SOPOT_WYSCIGI,
    SOPOT,
    SOPOT_KAMIENNY_POTOK,
    GDYNIA_ORLOWO,
    GDYNIA_REDLOWO,
    GDYNIA_WZGORZE_SW_MAKSYMILIANA,
    GDYNIA_GLOWNA;


    public static final List<Station> VALUES = Collections.unmodifiableList(Arrays.asList(values()));


    }
