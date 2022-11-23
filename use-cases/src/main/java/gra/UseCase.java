package gra;

import gra.boundaries.input.InputBoundary;
import gra.boundaries.output.OutputBoundary;

interface UseCase<T extends InputBoundary, U extends OutputBoundary> {

	U execute(T param);

}
