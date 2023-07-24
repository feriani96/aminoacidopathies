import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICasconfirme } from '../casconfirme.model';

@Component({
  selector: 'jhi-casconfirme-detail',
  templateUrl: './casconfirme-detail.component.html',
})
export class CasconfirmeDetailComponent implements OnInit {
  casconfirme: ICasconfirme | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casconfirme }) => {
      this.casconfirme = casconfirme;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
