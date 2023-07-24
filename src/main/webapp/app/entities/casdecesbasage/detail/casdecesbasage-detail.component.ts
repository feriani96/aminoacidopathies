import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICasdecesbasage } from '../casdecesbasage.model';

@Component({
  selector: 'jhi-casdecesbasage-detail',
  templateUrl: './casdecesbasage-detail.component.html',
})
export class CasdecesbasageDetailComponent implements OnInit {
  casdecesbasage: ICasdecesbasage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casdecesbasage }) => {
      this.casdecesbasage = casdecesbasage;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
