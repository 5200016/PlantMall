/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysBannerDeleteDialogComponent } from 'app/entities/sys-banner/sys-banner-delete-dialog.component';
import { SysBannerService } from 'app/entities/sys-banner/sys-banner.service';

describe('Component Tests', () => {
    describe('SysBanner Management Delete Component', () => {
        let comp: SysBannerDeleteDialogComponent;
        let fixture: ComponentFixture<SysBannerDeleteDialogComponent>;
        let service: SysBannerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysBannerDeleteDialogComponent]
            })
                .overrideTemplate(SysBannerDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysBannerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysBannerService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
