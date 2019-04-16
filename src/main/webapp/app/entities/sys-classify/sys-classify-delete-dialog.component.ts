import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from './sys-classify.service';

@Component({
    selector: 'jhi-sys-classify-delete-dialog',
    templateUrl: './sys-classify-delete-dialog.component.html'
})
export class SysClassifyDeleteDialogComponent {
    sysClassify: ISysClassify;

    constructor(
        protected sysClassifyService: SysClassifyService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysClassifyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysClassifyListModification',
                content: 'Deleted an sysClassify'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-classify-delete-popup',
    template: ''
})
export class SysClassifyDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysClassify }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysClassifyDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysClassify = sysClassify;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
