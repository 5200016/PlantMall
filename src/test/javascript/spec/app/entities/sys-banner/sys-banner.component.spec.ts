/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysBannerComponent } from 'app/entities/sys-banner/sys-banner.component';
import { SysBannerService } from 'app/entities/sys-banner/sys-banner.service';
import { SysBanner } from 'app/shared/model/sys-banner.model';

describe('Component Tests', () => {
    describe('SysBanner Management Component', () => {
        let comp: SysBannerComponent;
        let fixture: ComponentFixture<SysBannerComponent>;
        let service: SysBannerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysBannerComponent],
                providers: []
            })
                .overrideTemplate(SysBannerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysBannerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysBannerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysBanner(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysBanners[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
