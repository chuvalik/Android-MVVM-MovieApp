package com.example.feature_detail_screen.data.remote.dto

data class MovieDetailsResponse(
    val actorList: List<ActorDto>,
    val awards: String,
    val boxOffice: BoxOfficeDto,
    val companies: String,
    val companyList: List<CompanyDto>,
    val contentRating: String,
    val countries: String,
    val countryList: List<CountryDto>,
    val directorList: List<DirectorDto>,
    val directors: String,
    val errorMessage: Any,
    val fullCast: Any,
    val fullTitle: String,
    val genreList: List<GenreDto>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val image: String,
    val images: Any,
    val keywordList: List<String>,
    val keywords: String,
    val languageList: List<LanguageDto>,
    val languages: String,
    val metacriticRating: String,
    val originalTitle: String,
    val plot: String,
    val plotLocal: String,
    val plotLocalIsRtl: Boolean,
    val posters: Any,
    val ratings: Any,
    val releaseDate: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val similars: List<SimilarDto>,
    val starList: List<StarDto>,
    val stars: String,
    val tagline: String,
    val title: String,
    val trailer: Any,
    val tvEpisodeInfo: Any,
    val tvSeriesInfo: Any,
    val type: String,
    val wikipedia: Any,
    val writerList: List<WriterDto>,
    val writers: String,
    val year: String
)