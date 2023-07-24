import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICassuspecte } from '../cassuspecte.model';

@Component({
  selector: 'jhi-cassuspecte-detail',
  templateUrl: './cassuspecte-detail.component.html',
})
export class CassuspecteDetailComponent implements OnInit {
  cassuspecte: ICassuspecte | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cassuspecte }) => {
      this.cassuspecte = cassuspecte;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
