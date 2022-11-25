package gra.producer.json;

import static gra.boundaries.input.EmptyInputBoundary.emptyInputBoundary;
import static gra.producer.json.JSONProducerController.AWARDS_INTERVAL;
import static gra.producer.json.JSONProducerController.ROOT;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import gra.FindAwardsInterval;
import gra.boundaries.output.AwardIntervalOutputBoundary;
import gra.boundaries.output.AwardsIntervalOutputBoundary;

@WebMvcTest(JSONProducerController.class)
class JSONProducerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FindAwardsInterval findAwardsInterval;

	@Test
	void shouldExecuteSuccessfully() throws Exception {
		given(findAwardsInterval.execute(emptyInputBoundary()))
			.willReturn(
				AwardsIntervalOutputBoundary.builder()
					.min(new HashSet<>(
						singletonList(
							AwardIntervalOutputBoundary.builder()
								.producer("Mary")
								.previousWin(1980)
								.followingWin(1981)
								.interval(1)
								.build()
						)))
					.max(new HashSet<>(
						asList(
							AwardIntervalOutputBoundary.builder()
								.producer("Mary")
								.previousWin(1981)
								.followingWin(1990)
								.interval(9)
								.build(),
							AwardIntervalOutputBoundary.builder()
								.producer("John")
								.previousWin(2000)
								.followingWin(2009)
								.interval(9)
								.build()
						)))
					.build());

		mvc.perform(get(ROOT + AWARDS_INTERVAL).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())

			.andExpect(jsonPath("$.min.size()", is(1)))
			.andExpect(jsonPath("$.min[0].producer", is("Mary")))
			.andExpect(jsonPath("$.min[0].interval", is(1)))
			.andExpect(jsonPath("$.min[0].previousWin", is(1980)))
			.andExpect(jsonPath("$.min[0].followingWin", is(1981)))

			.andExpect(jsonPath("$.max.size()", is(2)))
			.andExpect(jsonPath("$.max[0].producer", is("Mary")))
			.andExpect(jsonPath("$.max[0].interval", is(9)))
			.andExpect(jsonPath("$.max[0].previousWin", is(1981)))
			.andExpect(jsonPath("$.max[0].followingWin", is(1990)))

			.andExpect(jsonPath("$.max[1].producer", is("John")))
			.andExpect(jsonPath("$.max[1].interval", is(9)))
			.andExpect(jsonPath("$.max[1].previousWin", is(2000)))
			.andExpect(jsonPath("$.max[1].followingWin", is(2009)))
		;
	}

}
